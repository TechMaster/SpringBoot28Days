package vn.techmaster.vincinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.vincinema.service.BookingService;
import vn.techmaster.vincinema.service.CinemaService;
import vn.techmaster.vincinema.service.EventService;
import vn.techmaster.vincinema.service.FilmService;
import vn.techmaster.vincinema.service.RoomService;

@Component
public class AppRunner implements CommandLineRunner{
  @Autowired private FilmService filmService;

  @Autowired private CinemaService cinemaService;

  @Autowired private RoomService roomService;

  @Autowired private EventService eventService;

  @Autowired private BookingService bookingService;
  
  @Override
  public void run(String... args) throws Exception {
    cinemaService.generateCinema();
    filmService.generateSampleFilms();
    roomService.generateRooms();
    eventService.generateEvents();
    bookingService.generateBookings();
  }
}
