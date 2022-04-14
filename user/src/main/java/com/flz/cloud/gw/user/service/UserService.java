package com.flz.cloud.gw.user.service;

import com.flz.cloud.gw.common.dto.JwtUser;
import com.flz.cloud.gw.common.utils.JwtUtils;
import com.flz.cloud.gw.user.dao.UserDao;
import com.flz.cloud.gw.user.dto.UserInfoDto;
import com.flz.cloud.gw.user.dto.UserLoginRequestDto;
import com.flz.cloud.gw.user.entity.User;
import com.flz.cloud.gw.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public JwtUser register(UserLoginRequestDto userLoginRequestDto) {
        String name = userLoginRequestDto.getName();
        String password = userLoginRequestDto.getPassword();

        if (userDao.existsByName(name)) {
            throw new UserException("用户名已存在");
        }

        User user = User.of(userLoginRequestDto);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        User savedUser = userDao.save(user);

        return JwtUtils.createJwtUser(savedUser.getId(), savedUser.getName());
    }

    public UserInfoDto getCurrUserInfo(JwtUser jwtUser) {
        return UserInfoDto.of(jwtUser);
    }

    public JwtUser login(UserLoginRequestDto userLoginRequestDto) {
        User user = userDao.findByNameAndPassword(userLoginRequestDto.getName(), DigestUtils.md5DigestAsHex(userLoginRequestDto.getPassword().getBytes()))
                .orElseThrow(() -> new UserException("用户名或密码错误"));

        return JwtUtils.createJwtUser(user.getId(), user.getName());
    }
}
