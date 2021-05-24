package vn.techmaster.vincinema.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Booking;
import vn.techmaster.vincinema.request.BookingRequest;
import vn.techmaster.vincinema.response.BookingInfo;
import vn.techmaster.vincinema.service.BookingService;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {
  @Autowired private BookingService bookingService;

  @PostMapping("/ticket")
  public ResponseEntity<Booking> createBooking(@ModelAttribute @Valid BookingRequest bookingRequest) {
    return ResponseEntity.ok().body(bookingService.createBooking(bookingRequest));
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookingInfo>> getBookingInfo() {
    return ResponseEntity.ok().body(bookingService.getBookingInfo());
  }

  @GetMapping("/ticket")
  public String showBookingForm(
  @ModelAttribute BookingRequest bookingRequest,  
  BindingResult bindingResult, 
  Model model) {
    if (! bindingResult.hasErrors()) {
      model.addAttribute("bookingRequest", bookingRequest); 
    }    
    return "bookTicket";
  }
}
