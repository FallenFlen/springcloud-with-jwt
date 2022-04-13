package com.flz.cloud.gw.common.utils;

import com.flz.cloud.gw.common.dto.JwtUser;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtils {
    //过期时间:一天
    private static long tokenExpiration = 24 * 60 * 60 * 1000;
    //签名秘钥
    private static String tokenSignKey = "gw-cloud-key";

    //根据参数生成token
    public static JwtUser createJwtUser(Long id, String name) {
        String token = Jwts.builder()
                .setSubject("cloud-gw")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("id", id)
                .claim("name", name)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();

        return JwtUser.builder()
                .id(id)
                .name(name)
                .token(token)
                .build();
    }

    public static <T> T getByKey(String token, String key, Class<T> type) {
        Claims claims = getClaims(token);
        return claims.get(key, type);
    }

    private static Claims getClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    public static void main(String[] args) {
        Long id = 1L;
        String name = "Test";
        JwtUser jwtUser = createJwtUser(id, name);
        System.out.println(jwtUser);
        System.out.println("----------------------");
        String token = jwtUser.getToken();
        Long currId = getByKey(token, "id", Long.class);
        String currName = getByKey(token, "name", String.class);
        System.out.println(String.format("id:%s name:%s", currId, currName));
    }
}
