package vn.techmaster.vincinema.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Booking;
import vn.techmaster.vincinema.request.BookingRequest;
import vn.techmaster.vincinema.service.BookingService;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {
  @Autowired private BookingService bookingService;

  @PostMapping("/")
  public ResponseEntity<Booking> createBooking(@RequestBody @Valid BookingRequest bookingRequest) {
    return ResponseEntity.ok().body(bookingService.createBooking(bookingRequest));
  }



}
