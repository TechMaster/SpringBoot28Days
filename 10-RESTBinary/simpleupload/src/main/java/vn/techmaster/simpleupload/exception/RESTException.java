package vn.techmaster.simpleupload.exception;
import org.springframework.http.HttpStatus;

public class RESTException extends RuntimeException {
  public HttpStatus status;
  public RESTException(String message, Throwable cause, HttpStatus status) {
    super(message, cause, false, false);
    this.status = status;
  }
  public RESTException(String message, HttpStatus status) {
    super(message, null, false, false);
    this.status = status;
  }

  public RESTException(String message) {
    super(message, null, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }

  public RESTException(String message, Throwable cause) {
    super(message, cause, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }
}