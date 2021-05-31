package vn.techmaster.seatbooking.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.seatbooking.exception.BookingException;
import vn.techmaster.seatbooking.model.BookingSeat;
import vn.techmaster.seatbooking.request.BookingRequest;
import vn.techmaster.seatbooking.service.BookingService;

@RestController
public class SeatBookingController {
  @Autowired private BookingService bookingService;
  
  @PostMapping("/book")
  public String book(@RequestBody BookingRequest bookingRequest) {
    try {
      bookingService.processBookingRequest(bookingRequest);
      return "Đặt chỗ thành công";
    } catch (BookingException e) {
      return e.getMessage();
    }    
  }

  @GetMapping("/book/all")
  public ResponseEntity<Collection<BookingSeat>> getAllBooking() {
    return ResponseEntity.ok().body(bookingService.getAllBooking());
  }

}
