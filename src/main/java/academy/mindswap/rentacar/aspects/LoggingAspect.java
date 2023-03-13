package academy.mindswap.rentacar.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
    Before is just to log every action for all controllers.
     */
    @Before("execution(* academy.mindswap.rentacar.controller.*.*(..))")
    public void checkUserBefore(JoinPoint joinPoint) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Logs.txt", true));

        writer.write("Before " + joinPoint.getSignature().getName() + " method call\n");
        writer.flush();
        logger.info("Before " + joinPoint.getSignature().getName() + " method call");
    }


//    /**
//     * Notificates everytime that a user is created.
//     * @param joinPoint
//     */
//    @AfterReturning(pointcut = "execution(* academy.mindswap.rentacar.controller.UserController.createUser())")
//    public void checkUserAfter(JoinPoint joinPoint, Object result) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter("Logs.txt"));
//
//        writer.write("Before " + joinPoint.getSignature().getName() + " method call\n");
//        writer.write("Response: " + result + "\n");
//
//        logger.info("Before " + joinPoint.getSignature().getName() + " method call");
//        logger.info("Response" + result);
//    }


//    @AfterThrowing(pointcut = "execution(* academy.mindswap.rentacar.controller.*(..))", throwing = "exception")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
//        logger.error("Exception in " + joinPoint.getSignature().getName() + " method call");
//        logger.error("Exception: " + exception);
//    }




}
