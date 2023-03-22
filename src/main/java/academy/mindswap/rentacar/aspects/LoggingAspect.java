package academy.mindswap.rentacar.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
    Before is just to log every action for all controllers.
     */
    @Before("execution(* academy.mindswap.rentacar.controller.*.*(..))")
    public void checkUserBefore(JoinPoint joinPoint) {
        logger.info("Method " + joinPoint.getSignature().getName() + " has been called!");
    }


    /**
     * Notificates everytime that a user is created.
     * @param joinPoint
     */
    @AfterReturning(pointcut = "execution(* academy.mindswap.rentacar.controller.UserController.createUser())", returning = "result")
    public void checkCarAfter(JoinPoint joinPoint, Object result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Logs.txt", true));
        writer.write("Method " + joinPoint.getSignature().getName() + " is Done\n");
        writer.write("Response: " + result + "\n");
        writer.flush();
        logger.info("Method " + joinPoint.getSignature().getName() + " is Done!");
        logger.info("Response:" + result);
    }


    @AfterThrowing(pointcut = "execution(* academy.mindswap.rentacar.controller.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Logs.txt", true));

        writer.write("Before " + joinPoint.getSignature().getName() + " method call\n");
        writer.write("Response: " + exception + "\n");
        writer.flush();

        logger.error("Exception in " + joinPoint.getSignature().getName() + " method call");
        logger.error("Exception: " + exception);
    }


    @Around("execution(* academy.mindswap.rentacar.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info("Before " + joinPoint.getSignature().getName() + " method call");
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("After " + joinPoint.getSignature().getName() + " method call");
        logger.info("Execution time of " + joinPoint.getSignature().getName() + " method call: " + (endTime - startTime) + " milliseconds");
        return result;
    }



}
