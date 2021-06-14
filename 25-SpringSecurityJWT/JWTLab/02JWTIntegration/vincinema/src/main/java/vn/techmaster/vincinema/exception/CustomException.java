package vn.techmaster.vincinema.exception;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
  public HttpStatus status;
  public CustomException(String message, Throwable cause, HttpStatus status) {
    super(message, cause, false, false);
    this.status = status;
  }

  public CustomException(String message, HttpStatus status) {
    super(message, null, false, false);
    this.status = status;
  }

  public CustomException(String message) {
    super(message, null, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }

  public CustomException(String message, Throwable cause) {
    super(message, cause, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }
}
