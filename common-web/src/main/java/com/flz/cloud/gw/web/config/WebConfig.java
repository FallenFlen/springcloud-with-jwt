package com.flz.cloud.gw.web.config;

import com.flz.cloud.gw.web.interceptor.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final RequestInterceptor requestInterceptor;
    private final List<String> paths = List.of(
            "/user/**",
            "/s1/**"
    );
    private final List<String> excludePaths = List.of(
            "/user",
            "/user/login"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns(paths).excludePathPatterns(excludePaths);
    }
}
