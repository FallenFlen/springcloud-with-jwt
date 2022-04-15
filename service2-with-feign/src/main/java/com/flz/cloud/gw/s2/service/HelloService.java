package com.flz.cloud.gw.s2.service;

import com.flz.cloud.gw.clients.S1Client;
import com.flz.cloud.gw.common.dto.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final S1Client s1Client;

    public String hello(JwtUser jwtuser) {
        String s1Result = s1Client.hello();
        return String.format("s2 with '%s' and s1 with '%s'", jwtuser.getName(), s1Result);
    }
}
