package vn.techmaster.seatbooking.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingSeat {
  String seatNo;
  Customer customer;
  LocalDateTime bookedTime;
}
