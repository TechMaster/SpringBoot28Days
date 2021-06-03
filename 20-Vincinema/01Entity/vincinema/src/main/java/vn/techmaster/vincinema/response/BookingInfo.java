package vn.techmaster.vincinema.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingInfo {
  private long bookId;
  private String mobile;
  private String email;
  private String filmTitle;
  private String cinemaName;
  private String date;
  private String beginAt;
  private String seats;
  private long amount;
  private Date createdOn;
  
}