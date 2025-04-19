package com.learning.springhealth.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component
@RequiredArgsConstructor
public class MetricsAspect {

    private final CustomMetricService customMetricService;

    @Around("execution(* com.learning.springhealth.service..*(..))")
    public Object monitorServiceExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        try {
            Object result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            customMetricService.incrementExceptionCount(methodName, e.getClass().getSimpleName());
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            Duration duration = Duration.ofMillis(end - start);
            customMetricService.recordExecutionTime(methodName, duration);
        }
    }
}
