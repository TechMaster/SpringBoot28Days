package vn.techmaster.bmiservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException 
{
  private static final long serialVersionUID = -8911423154750344009L;
  private String model;
  public RecordNotFoundException(String exception, String model) {
      super(exception);
      this.model = model;
  }
  public String getModel() {
    return model;
  }
}