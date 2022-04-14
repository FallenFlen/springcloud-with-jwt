package com.flz.cloud.gw.user.dto;

import com.flz.cloud.gw.common.dto.JwtUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String name;

    public static UserInfoDto of(JwtUser jwtUser) {
        return new UserInfoDto(jwtUser.getId(), jwtUser.getName());
    }
}
