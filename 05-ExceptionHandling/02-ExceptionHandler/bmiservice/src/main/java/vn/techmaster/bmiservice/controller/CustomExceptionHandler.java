package vn.techmaster.bmiservice.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import vn.techmaster.bmiservice.exception.APIError;
import vn.techmaster.bmiservice.exception.RecordNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(value = {Exception.class, RuntimeException.class})
  public final ResponseEntity<APIError> handleAllExceptions(Exception ex, WebRequest request) {
    APIError apiError = new APIError("Generic Exception", ex.getLocalizedMessage());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  
  @ExceptionHandler(RecordNotFoundException.class)
  public final ResponseEntity<APIError> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
    APIError apiError = new APIError("Record Not Found", ex.getLocalizedMessage(), "additional explanation", "and root cause");
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }
  
  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    APIError apiError = new APIError("Argument type mismatch", ex.getLocalizedMessage());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    APIError apiError = new APIError("No handler found", ex.getLocalizedMessage());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

}