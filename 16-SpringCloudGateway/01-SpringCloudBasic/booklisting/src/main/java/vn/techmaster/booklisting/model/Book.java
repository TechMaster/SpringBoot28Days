package vn.techmaster.booklisting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
  private String title;
  private String author;  
}
