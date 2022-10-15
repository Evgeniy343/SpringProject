package by.evgen.cafe.advice;

import by.evgen.cafe.exception.MealNotFoundException;
import by.evgen.cafe.exception.UserNotFoundException;
import by.evgen.cafe.handler.CafeModelNotFoundExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = CafeModelNotFoundExceptionHandler.class)
public class CafeModelAdvice {
    @ExceptionHandler({UserNotFoundException.class, MealNotFoundException.class})
    public ResponseEntity<Response> handleException(Exception e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
