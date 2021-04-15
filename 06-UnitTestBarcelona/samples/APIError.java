package com.onemount.barcelonateam.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIError {
  public String message;
  public List<String> details;



  public APIError(String message, String ... details) {
    this.message = message;
    this.details = Arrays.asList(details);
  }

  public APIError(Exception ex) {
    String classNameOfException = ex.getClass().getName();
    int lastDotIndex = classNameOfException.lastIndexOf(".");
    String shortExceptionName;
    if (lastDotIndex > 0) {
      shortExceptionName = classNameOfException.substring(lastDotIndex + 1);
    } else {
      shortExceptionName = classNameOfException;
    }

    this.message = shortExceptionName + " : " + ex.getMessage();
    if (ex.getCause() != null) {
      this.details = new ArrayList<>();
      this.details.add(ex.getCause().getMessage());       
    }
  }
}