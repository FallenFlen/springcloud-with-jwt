package com.flz.cloud.gw.s1.config;

import com.flz.cloud.gw.web.config.WebConfig;
import com.flz.cloud.gw.web.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Service1Config {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }

    @Bean
    public WebConfig webConfig() {
        return new WebConfig(requestInterceptor());
    }
}
