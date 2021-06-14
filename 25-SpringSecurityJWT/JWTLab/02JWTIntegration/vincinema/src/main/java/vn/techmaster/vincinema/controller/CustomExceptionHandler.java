package vn.techmaster.vincinema.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import vn.techmaster.vincinema.exception.APIError;
import vn.techmaster.vincinema.exception.CustomException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(value = {Exception.class, RuntimeException.class})
  public final ResponseEntity<APIError> handleAllExceptions(Exception ex, WebRequest request) {
    APIError apiError;
    Throwable cause = ex.getCause();
    if (cause != null) {
      apiError = new APIError("Generic Exception", ex.getLocalizedMessage(), ex.getCause().getMessage());
    } else {
      apiError = new APIError("Generic Exception", ex.getLocalizedMessage());
    }
    
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }
  
  
  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<APIError> handleCustomException(CustomException ex, WebRequest request) {
    APIError apiError = new APIError(ex);
    return new ResponseEntity<>(apiError, ex.status);
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
