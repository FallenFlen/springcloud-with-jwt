package com.flz.cloud.gw.s2.controller;

import com.flz.cloud.gw.common.dto.JwtUser;
import com.flz.cloud.gw.s2.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s2")
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestAttribute("jwtUser") JwtUser jwtUser) {
        return helloService.hello(jwtUser);
    }
}
