package com.flz.cloud.gw.user.controller;

import com.flz.cloud.gw.common.dto.JwtUser;
import com.flz.cloud.gw.user.dto.UserInfoDto;
import com.flz.cloud.gw.user.dto.UserLoginRequestDto;
import com.flz.cloud.gw.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public JwtUser register(@RequestBody @Validated UserLoginRequestDto userLoginRequestDto) {
        return userService.register(userLoginRequestDto);
    }

    @GetMapping("/info")
    public UserInfoDto info(String token) {
        return userService.getCurrUserInfo(token);
    }

    @PostMapping("/login")
    public JwtUser login(@RequestBody @Validated UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }
}
