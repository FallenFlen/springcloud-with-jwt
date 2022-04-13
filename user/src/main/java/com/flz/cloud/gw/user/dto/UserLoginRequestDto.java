package com.flz.cloud.gw.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    private String password;
}
