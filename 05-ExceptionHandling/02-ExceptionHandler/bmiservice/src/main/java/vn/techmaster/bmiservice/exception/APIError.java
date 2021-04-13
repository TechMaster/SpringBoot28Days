package vn.techmaster.bmiservice.exception;

import java.util.Arrays;
import java.util.List;

public class APIError {
  public String message;
  public List<String> details;
  public APIError(String message, List<String> details) {
    this.message = message;
    this.details = details;
  }

  public APIError(String message, String ... details) {
    this.message = message;
    this.details = Arrays.asList(details);
  }
}
