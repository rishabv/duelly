package com.duelly.configurations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspectConfig {
    @Around("@annotation(com.duelly.annotations.LogExecutionTime)")
    public Object logExecution(ProceedingJoinPoint joinPoint ) throws Throwable {
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String method = signature.getDeclaringType().getSimpleName() + "." + signature.getName();
        Object[] args = joinPoint.getArgs();
        log.info("Executing Method: {} ", method);
        if(args!=null && args.length > 0) {
            log.info("Args {} ", Arrays.toString(args));
        }
        try {
            Object result = joinPoint.proceed(); // Proceed with method call
            long timeTaken = System.currentTimeMillis() - start;

            log.info("✅  --------------- Method {} completed in {} ms", method, timeTaken);
            return result;
        } catch (Exception ex) {
            long timeTaken = System.currentTimeMillis() - start;
            log.error("Method {} failed in {} ms with exception: {}",
                    method, timeTaken, ex.getMessage(), ex);
            throw ex;
        }
    }
}
