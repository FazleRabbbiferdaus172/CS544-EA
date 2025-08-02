package bank.service.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class JMSLogAdvice {
    @Autowired
    private ILogger logger;

    @After("execution (* bank.jms.*.sendJMSMessage(..)) && args(text)")
    public void logJMSMessage(JoinPoint jp, String text) {
        logger.log("JMS text: "+text);
    }
}
