package com.bear.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bear.model.TokenType;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * JWT 签发类
 *
 * @author moyulingjiu
 * create 2022年5月11日
 */
public class JwtIssuer {
    /**
     * token过期时间
     */
    private static final Long EXPIRE_TIME = DurationTimeUtil.TWO_HOUR;
    /**
     * 即将过期时间
     */
    public static final Long WILL_EXPIRE_TIME = DurationTimeUtil.TEN_MINUTE;

    /**
     * 签发token
     *
     * @param id   用户id
     * @param name 用户名
     * @param type token类型
     * @return 返回值
     */
    public static String getToken(@NotNull Long id, @NotNull String name, @NotNull TokenType type) {
        return JWT.create()
                // 过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
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
