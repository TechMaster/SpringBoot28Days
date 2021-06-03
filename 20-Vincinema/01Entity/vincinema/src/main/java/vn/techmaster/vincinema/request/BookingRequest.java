package vn.techmaster.vincinema.request;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookingRequest {
  private String filmTitle;
  private String cinema;
  @Pattern(regexp = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$",
  message="Ngày phải theo định dang yyyy-mm-dd")
  private String date;

  @Pattern(regexp = "^\\d{10,11}$", message = "Số di động phải có từ 10 đến 11 chữ số")
  private String mobile;

  @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", 
  message="Email không hợp lệ")
  private String email;
 
  private String seats;
}
