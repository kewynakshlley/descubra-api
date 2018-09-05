package co.descubra.descubraapi.exceptions;
 
import java.util.Date;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
     
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest wr){
        ExceptionResponse exceptionResponse = new
                ExceptionResponse(new Date(), ex.getMessage(), wr.getDescription(false));
         
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
     
    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Object> handleDataNotFoundExceptions(DataNotFoundException ex, WebRequest wr){
        ExceptionResponse exceptionResponse = new
                ExceptionResponse(new Date(), ex.getMessage(), wr.getDescription(false));
         
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
     
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse ep = new ExceptionResponse(new Date(), "Validation error!", ex.getBindingResult().toString());
        return new ResponseEntity<>(ep, HttpStatus.BAD_REQUEST);
    }
 
}
