package com.bear.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JwtTest {
    @Test
    public void test1() {
        String token = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()))  //设置过期时间
                .withAudience("user1") //设置接受方信息，一般时登录用户
                .sign(Algorithm.HMAC256("111111"));  //使用HMAC算法，111111作为密钥加密
        System.out.println(token);

        String userId = JWT.decode(token).getAudience().get(1);
        System.out.println(userId);
        System.out.println(JWT.decode(token).getAudience());
        Assertions.assertEquals("user1", userId);
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("111111")).build();
        jwtVerifier.verify(token);
    }
}
