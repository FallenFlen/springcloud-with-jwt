package com.flz.cloud.gw.s2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.flz.cloud.gw"})
@EnableFeignClients(basePackages = {"com.flz.cloud.gw"})
public class S2Application {
    public static void main(String[] args) {
        SpringApplication.run(S2Application.class, args);
    }
}
