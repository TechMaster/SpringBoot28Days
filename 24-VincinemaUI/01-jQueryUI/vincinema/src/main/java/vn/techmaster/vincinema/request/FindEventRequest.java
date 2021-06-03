package vn.techmaster.vincinema.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindEventRequest {
  private String filmTitle;
  private Long cinemaID;  
  private String showDate;
}
