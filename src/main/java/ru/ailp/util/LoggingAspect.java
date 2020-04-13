package ru.ailp.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Around("execution(* ru.ailp.service.*.*(..)) || execution(* ru.ailp.service.abstr.*.*(..))")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = joinPoint.proceed();
        logger.info("объект на выходе:\n {}", returnValue);
        return returnValue;
    }
}