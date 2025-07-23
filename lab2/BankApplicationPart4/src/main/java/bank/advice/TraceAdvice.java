package bank.advice;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TraceAdvice {
    @Autowired
    private ILogger logger;

    @Before("execution (* bank.dao.*.*(..))")
    public void traceBefore(JoinPoint joinPoint) {
        logger.log("Calling method: "+ joinPoint.getSignature().getName());
    }
}
