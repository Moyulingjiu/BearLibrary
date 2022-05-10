package com.bear.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bear.model.TokenType;
import com.bear.util.*;
import io.swagger.models.auth.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * token验证
 *
 * @author moyulingjiu
 */
@Component
@Aspect
public class LoginAspect {
    private static final Logger logger = LogManager.getLogger(LoginAspect.class);

    @Pointcut("@annotation(com.bear.login.UserLoginCheck)")
    public void userLogin() {
    }

    @Pointcut("@annotation(com.bear.login.AdminLoginCheck)")
    public void adminLogin() {
    }

    @Pointcut("@annotation(com.bear.login.LoginCheck)")
    public void login() {
    }

    @Around("userLogin()")
    public Object userTokenCheck(ProceedingJoinPoint joinPoint) {
        return checkToken(joinPoint, TokenType.USER);
    }

    @Around("adminLogin()")
    public Object adminTokenCheck(ProceedingJoinPoint joinPoint) {
        return checkToken(joinPoint, TokenType.ADMIN);
    }

    @Around("login()")
    public Object tokenCheck(ProceedingJoinPoint joinPoint) {
        return checkToken(joinPoint, TokenType.ALL);
    }

    public Object checkToken(ProceedingJoinPoint joinPoint, TokenType tokenType) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return ResponseUtil.badToken();
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        if (token != null && token.length() > 10) {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Common.getTokenSecret())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                return ResponseUtil.badToken();
            }
            long userId;
            try {
                userId = Long.parseLong(JWT.decode(token).getAudience().get(0));
            } catch (NumberFormatException e) {
                return ResponseUtil.badToken();
            }
            String userName = JWT.decode(token).getClaim("name").asString();
            int type;
            try {
                type = Integer.parseInt(JWT.decode(token).getSubject());
            } catch (NumberFormatException e) {
                return ResponseUtil.badToken();
            }
            if (type != tokenType.ordinal() && tokenType != TokenType.ALL) {
                return ResponseUtil.badToken();
            }

            Date expiresAt = JWT.decode(token).getExpiresAt();
            Date issuedAt = JWT.decode(token).getIssuedAt();

            // 如果token过期了，或者签发时间甚至是未来，那么该token有问题
            if (issuedAt.after(new Date())) {
                return ResponseUtil.badToken();
            }
            if (expiresAt.before(new Date())) {
                return ResponseUtil.decorateReturnObject(ReturnNo.EXPIRE_TOKEN);
            }
            // 如果有效期不足十分钟（返回新的token给前端）
            if (System.currentTimeMillis() + JwtIssuer.WILL_EXPIRE_TIME > expiresAt.getTime()) {
                String newToken = JwtIssuer.getToken(userId, userName, tokenType);
                return ResponseUtil.decorateReturnObject(ReturnNo.NEED_CHANGE_TOKEN, newToken);
            }

            // 对参数进行注入（主要是LoginId、LoginName）
            Object[] args = joinPoint.getArgs();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                for (Annotation annotation : annotations[i]) {
                    if (annotation.annotationType() == LoginId.class) {
                        args[i] = userId;
                        break;
                    } else if (annotation.annotationType() == LoginName.class) {
                        args[i] = userName;
                        break;
                    }
                }
            }
            try {
                return joinPoint.proceed(args);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseUtil.badToken();

    }
}
