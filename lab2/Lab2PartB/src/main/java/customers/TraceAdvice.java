package customers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class TraceAdvice {
    @After("execution(* customers.EmailSender.sendEmail(..)) && args(email, message)")
    public void traceAfterMethod(JoinPoint joinPoint, String email, String message) {
        System.out.println(LocalDateTime.now()+" method="+joinPoint.getSignature().getName()+ " address="+email+"\n" +
                "message= "+message+" \n outgoing mail server="+((EmailSender)joinPoint.getTarget()).outgoingMailServer);
    }
}
