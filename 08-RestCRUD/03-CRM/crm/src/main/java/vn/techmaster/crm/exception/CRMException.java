package vn.techmaster.crm.exception;

import org.springframework.http.HttpStatus;

public class CRMException extends RuntimeException {
  public HttpStatus status;
  public CRMException(String message, Throwable cause, HttpStatus status) {
    super(message, cause, false, false);
    this.status = status;
  }

  public CRMException(String message, HttpStatus status) {
    super(message, null, false, false);
    this.status = status;
  }

  public CRMException(String message) {
    super(message, null, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }

  public CRMException(String message, Throwable cause) {
    super(message, cause, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }
}
