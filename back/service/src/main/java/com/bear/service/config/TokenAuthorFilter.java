package com.bear.service.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bear.service.util.StringUtils;
import com.bear.util.Common;
import com.bear.util.ResponseUtil;
import com.bear.util.ReturnNo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * token验证
 *
 * @author moyulingjiu
 */
@Component
public class TokenAuthorFilter implements HandlerInterceptor {
    private void prohibit(HttpServletResponse response) {
        try (PrintWriter printWriter = response.getWriter()) {
            Object o = ResponseUtil.decorateReturnObject(ReturnNo.FORBIDDEN);
            printWriter.write(o.toString());
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if (StringUtils.isNotEmpty(token)) {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Common.getTokenSecret())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                prohibit(response);
                return false;
            }
            long userId;
            try {
                userId = Long.parseLong(JWT.decode(token).getAudience().get(0));
            } catch (NumberFormatException e) {
                prohibit(response);
                return false;
            }
            String userName = JWT.decode(token).getClaim("userName").asString();
            String type = JWT.decode(token).getSubject();
            Date expiresAt = JWT.decode(token).getExpiresAt();
            Date issuedAt = JWT.decode(token).getIssuedAt();
            System.out.println("token验证通过");
            System.out.println(userId);
            System.out.println(userName);
            System.out.println(type);
            System.out.println(expiresAt);
            System.out.println(issuedAt);
            return true;
        }
        prohibit(response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
