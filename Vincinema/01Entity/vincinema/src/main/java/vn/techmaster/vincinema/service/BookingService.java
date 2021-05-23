package vn.techmaster.vincinema.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.vincinema.model.Booking;
import vn.techmaster.vincinema.model.Customer;
import vn.techmaster.vincinema.model.Event;
import vn.techmaster.vincinema.model.Seat;
import vn.techmaster.vincinema.repository.EventRepository;
import vn.techmaster.vincinema.repository.RoomRepository;
import vn.techmaster.vincinema.repository.SeatRepository;

@Service
public class BookingService {
  @PersistenceContext private EntityManager em;

  @Autowired private EventRepository eventRepo;

  @Autowired private SeatRepository seatRepo;

  @Transactional
  public void generateBookings() {
    createBooking(
      "TRÙM CUỐI", 
      "Vincom Nguyễn Chí Thanh",
      "2021-05-24",
      "0902209011", "cuong@techmaster.vn",
      "A10,A11"
    );

    createBooking(
      "BÀN TAY DIỆT QUỶ", 
      "Vincom Center Bà Triệu",
      "2021-05-23",
      "0982299018", "long@techmaster.vn",
      "D12,D13,D14"
    );

    createBooking(
      "PALM SPRINGS", 
      "Royal City",
      "2021-05-23",
      "0882299018", "huong@gmail.com",
      "B8,B9"
    );
  }

  //Cần kiểm tra duplicate
  public void createBooking(
    String filmTitle, String cinemaName, String date, 
    String mobile, String email,
    String seatNames) {
    List<Event> events = eventRepo.findByFilmCinemaDate(filmTitle, cinemaName, date);
    if (!events.isEmpty()) {
      Event event = events.get(0);
      Customer customer = Customer.builder()
      .mobile(mobile)
      .email(email)
      .build();

      em.persist(customer);

      List<Seat> seats = seatRepo.findByRoomIdAndNameIn(event.getRoom().getId(), seatNames.split(","));
      
      Booking booking = Booking.builder()
      .event(event)
      .customer(customer) 
      .seats(seats)
      .build();

      em.persist(booking);
    }
  }
  
}
