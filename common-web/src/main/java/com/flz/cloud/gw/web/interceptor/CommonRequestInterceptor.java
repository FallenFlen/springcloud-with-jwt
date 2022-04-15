package com.flz.cloud.gw.web.interceptor;

import com.flz.cloud.gw.common.constants.Constant;
import com.flz.cloud.gw.common.dto.ErrorResult;
import com.flz.cloud.gw.common.dto.JwtUser;
import com.flz.cloud.gw.common.utils.JsonUtils;
import com.flz.cloud.gw.common.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CommonRequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(Constant.TOKEN_KEY);
        if (!JwtUtils.validate(token)) {
            responseError(response);
            return false;
        }

        Long id = JwtUtils.getByKey(token, "id", Long.class);
        String name = JwtUtils.getByKey(token, "name", String.class);
        JwtUser jwtUser = JwtUtils.createJwtUser(id, name);
        request.setAttribute("jwtUser", jwtUser);
        return true;
    }

    private void responseError(HttpServletResponse response) {
        response.setStatus(401);
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            response.getWriter().write(JsonUtils.toJson(new ErrorResult("token无效")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.removeAttribute("jwtUser");
    }
}
