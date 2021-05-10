package sit.int221.ppclothes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Object> handleExceptions(ProductException productException,WebRequest webRequest){
        ExceptionRepo response = new ExceptionRepo(productException.getError_code(),productException.getMessage(),LocalDateTime.now());
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }
}