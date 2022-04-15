package com.flz.cloud.gw.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("service1")
public interface S1Client {
    @GetMapping("/s1/hello")
    String hello();
}
