package vn.techmaster.bookstore.exception;
import java.util.List;
import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {
  private static final long serialVersionUID = -7532434951993672739L;
  
  private HttpStatus httpStatus;
  private String message;
  private List<String> details;

  public APIException(HttpStatus httpStatus, String message, List<String> details){
    super(message);
    this.httpStatus = httpStatus;
    this.details = details;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public List<String> getDetails() {
    return details;
  }
}
