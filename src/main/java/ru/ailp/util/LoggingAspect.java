package ru.ailp.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("execution(* ru.ailp.service.*.*(..)) || execution(* ru.ailp.service.abstr.*.*(..))")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = joinPoint.proceed();
        log.info("объект на выходе:\n {}", returnValue);
        return returnValue;
    }
}