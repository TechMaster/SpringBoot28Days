package vn.techmaster.seatbooking.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingRequest {
  private String mobile;
  private String seats;  //Số ghế cách nhau bằng dấu phẩy
}
