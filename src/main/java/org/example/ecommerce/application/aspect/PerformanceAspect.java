package org.example.ecommerce.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class PerformanceAspect {
    @Around("@annotation(Performance)")
    public Object performance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        if (executionTime > 5000)
            log.warn(
                    "Long Running Request: {} ({} milliseconds)",
                    joinPoint.getSignature(),
                    executionTime
            );
        else
            log.info(String.format(
                    "%s executed in %s ms",
                    joinPoint.getSignature(),
                    executionTime
            ));
        return proceed;
    }
}
