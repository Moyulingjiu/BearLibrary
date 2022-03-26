package com.bear.logger;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志注入
 *
 * @author moyulingjiu
 * create 2022年3月26日
 */
@Aspect
@Component
public class AuditAspect {
    private final Logger logger = LoggerFactory.getLogger(AuditAspect.class);
}
