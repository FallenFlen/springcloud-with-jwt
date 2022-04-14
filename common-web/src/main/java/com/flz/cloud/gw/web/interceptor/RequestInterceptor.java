package com.flz.cloud.gw.web.interceptor;

import com.flz.cloud.gw.common.constants.Constant;
import com.flz.cloud.gw.common.dto.JwtUser;
import com.flz.cloud.gw.common.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(Constant.TOKEN_KEY);
        if (!StringUtils.isEmpty(token)) {
            Long id = JwtUtils.getByKey(token, "id", Long.class);
            String name = JwtUtils.getByKey(token, "name", String.class);
            JwtUser jwtUser = JwtUtils.createJwtUser(id, name);
            request.setAttribute("jwtUser", jwtUser);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.removeAttribute("jwtUser");
    }
}
