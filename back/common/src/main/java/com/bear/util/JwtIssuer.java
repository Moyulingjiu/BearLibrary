package com.bear.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bear.model.TokenType;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class JwtIssuer {
    /**
     * 签发token
     *
     * @param id 用户id
     * @param name 用户名
     * @param type token类型
     * @return 返回值
     */
    public static String getToken(@NotNull Long id, @NotNull String name, @NotNull TokenType type) {
        return JWT.create()
                // 过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + DurationTimeUtil.DAY))
                // 签发时间
                .withIssuedAt(new Date(System.currentTimeMillis()))
                // 接收人
                .withAudience(id.toString())
                // 签发的对象类型
                .withSubject(type.ordinal() + "")
                // token id
                .withJWTId(UUID.randomUUID().toString().replaceAll("-", ""))
                // 接收人昵称
                .withClaim("name", name)
                // 加密
                .sign(Algorithm.HMAC256(Common.getTokenSecret()));
    }
}
