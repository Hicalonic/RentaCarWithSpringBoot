package academy.mindswap.rentacar.aspects;

import academy.mindswap.rentacar.exceptions.EmailException;
import academy.mindswap.rentacar.exceptions.LicensePlateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class UserExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @ExceptionHandler(value = {EmailException.class})
    public ResponseEntity<String> checkEmailDuplicate (Exception ex) {
        logger.error("Known Exception" + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(value = {LicensePlateException.class})
    public ResponseEntity<String> checkLicensePlate (Exception ex) {
        logger.error("Known Exception" + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }



}
