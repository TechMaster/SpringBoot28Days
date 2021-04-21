package vn.techmaster.bookstore.model;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPOJO {
  @Size(min = 2, max = 200, message = "title's length must be between 2 and 200")
  public String title;

  @Size(min = 2, max = 100, message = "author's length must be between 2 and 100")
  public String author;
}

