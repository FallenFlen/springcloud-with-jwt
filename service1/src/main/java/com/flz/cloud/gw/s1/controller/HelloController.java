package com.flz.cloud.gw.s1.controller;

import com.flz.cloud.gw.common.dto.JwtUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s1")
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestAttribute("jwtUser") JwtUser jwtUser) {
        return "hello " + jwtUser.getName();
    }
}
