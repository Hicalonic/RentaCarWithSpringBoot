package academy.mindswap.rentacar.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* academy.mindswap.rentacar.controller.UserController.createUser())")
    public void checkUserBefore(JoinPoint joinPoint) {
        logger.info("Before " + joinPoint.getSignature().getName() + " method call");
    }

    @AfterReturning(pointcut = "execution(* academy.mindswap.rentacar.controller.*)")
    public void checkUserAfter(JoinPoint joinPoint) {
        logger.info("Before " + joinPoint.getSignature().getName() + " method call");
    }


}
