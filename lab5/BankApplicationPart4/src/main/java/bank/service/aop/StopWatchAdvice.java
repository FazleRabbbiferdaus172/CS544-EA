package bank.service.aop;

import bank.logging.ILogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class StopWatchAdvice {
    @Autowired
    private ILogger logger;

    @Around("execution (* bank.service.*.*(..))")
    public Object methodTimer(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object val = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        System.out.println("Runttime for service " + call.getSignature().getName() + " : " + totaltime);
        return val;
    }
}
