package vn.techmaster.simpleupload.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import lombok.extern.slf4j.Slf4j;
import vn.techmaster.simpleupload.exception.RESTException;


@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  
  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    APIError apiError = new APIError("Binding Exception", ex.getMessage());
    log.error(apiError.message, apiError.details);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Exception.class, RuntimeException.class})
  public final ResponseEntity<APIError> handleAllExceptions(Exception ex, WebRequest request) {
    APIError apiError = new APIError("Generic Exception", ex.getMessage());
    log.error(apiError.message, apiError.details);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RESTException.class)
  public final ResponseEntity<APIError> handleBookStoreException(RESTException ex, WebRequest request) {
    APIError apiError = new APIError(ex);
    log.error(apiError.message, apiError.details);
    return new ResponseEntity<>(apiError, ex.status);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    APIError apiError = new APIError(ex);
    log.error(apiError.message, apiError.details);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }
  
  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    APIError apiError = new APIError("Argument type mismatch", ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    APIError apiError = new APIError("No handler found", ex.getMessage());
    log.error(apiError.message, apiError.details);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

}