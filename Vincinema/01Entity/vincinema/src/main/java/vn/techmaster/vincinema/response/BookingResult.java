package vn.techmaster.vincinema.response;

import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookingResult {
  private long booking_id;
  private long totalAmount;
}
