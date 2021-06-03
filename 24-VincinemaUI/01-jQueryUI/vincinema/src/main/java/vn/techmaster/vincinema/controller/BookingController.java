package vn.techmaster.vincinema.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.vincinema.model.Booking;
import vn.techmaster.vincinema.model.Cinema;
import vn.techmaster.vincinema.model.Event;
import vn.techmaster.vincinema.model.Room;
import vn.techmaster.vincinema.request.BookingRequest;
import vn.techmaster.vincinema.request.FindEventRequest;
import vn.techmaster.vincinema.response.BookingInfo;
import vn.techmaster.vincinema.service.BookingService;
import vn.techmaster.vincinema.service.CinemaService;
import vn.techmaster.vincinema.service.EventService;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {
  @Autowired private BookingService bookingService;
  @Autowired private CinemaService cinemaService;
  @Autowired private EventService eventService;
  
  @PostMapping("/ticket")
  public ResponseEntity<Booking> createBooking(@ModelAttribute @Valid BookingRequest bookingRequest) {
    return ResponseEntity.ok().body(bookingService.createBooking(bookingRequest));
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BookingInfo>> getBookingInfo() {
    return ResponseEntity.ok().body(bookingService.getBookingInfo());
  }

  @GetMapping("/findevent")
  public String showFindEventForm(Model model) {
    FindEventRequest findEventRequest = new FindEventRequest();
    model.addAttribute("findEventRequest", findEventRequest);
    List<Cinema> cinemas = cinemaService.findAll();
    model.addAttribute("allCinemas", cinemas);
    return "findevent.html";
  }

  @PostMapping("/findevent")
  public String findEvent(@ModelAttribute("findEventRequest") FindEventRequest findEventRequest, BindingResult bindingResult, Model model) {
    List<Event> events = null;
    if (!bindingResult.hasErrors()) {
      events = eventService.findByEventRequest(findEventRequest);      
    }
    if (events == null || events.isEmpty()) {
      return "redirect:/booking/findevent";
    }
    model.addAttribute("events", events);
    return "find_event_result";
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

  @GetMapping("/event/{event_id}")
  public String bookTicketAtEvent(@PathVariable("event_id") Long event_id, Model model) {
    Optional<Event> oEvent = eventService.findByEventId(event_id);
    if (oEvent.isPresent()) {
      Event event = oEvent.get();
      model.addAttribute("event", event);
      Room room = event.getRoom();
      String[][] seatLayout = room.getSeatLayout();      
      model.addAttribute("seatLayout", seatLayout);
      return "book_event";
    } else {
      return "redirect:/booking/findevent";
    }
    
  }
}
