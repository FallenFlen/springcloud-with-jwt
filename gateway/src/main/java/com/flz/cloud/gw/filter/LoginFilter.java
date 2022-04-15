package com.flz.cloud.gw.filter;

import com.flz.cloud.gw.common.constants.Constant;
import com.flz.cloud.gw.common.dto.ErrorResult;
import com.flz.cloud.gw.common.utils.JsonUtils;
import com.flz.cloud.gw.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoginFilter implements GlobalFilter, Ordered {
    private static final String LOGIN_URI = "/user/login";
    private static final String REGISTER_URI = "/user";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();

        // 放行登录/注册
        String currentUri = request.getPath().toString();
        if (LOGIN_URI.equals(currentUri) || REGISTER_URI.equals(currentUri)) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(Constant.TOKEN_KEY);
        boolean valid = JwtUtils.validate(token);
        if (!valid) {
            return deny(response, new ErrorResult("token无效"));
        }

        return chain.filter(exchange);
    }

    private Mono<Void> deny(ServerHttpResponse response, ErrorResult errorResult) {
        byte[] errorBytes = JsonUtils.toJson(errorResult).getBytes(StandardCharsets.UTF_8);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer dataBuffer = response.bufferFactory().wrap(errorBytes);
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
