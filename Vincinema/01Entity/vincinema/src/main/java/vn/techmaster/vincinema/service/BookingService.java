package vn.techmaster.vincinema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.vincinema.model.Booking;
import vn.techmaster.vincinema.model.BookingSeat;
import vn.techmaster.vincinema.model.Customer;
import vn.techmaster.vincinema.model.Event;
import vn.techmaster.vincinema.model.Seat;
import vn.techmaster.vincinema.repository.EventRepository;
import vn.techmaster.vincinema.repository.SeatRepository;
import vn.techmaster.vincinema.request.BookingRequest;

@Service
public class BookingService {
  @PersistenceContext private EntityManager em;

  @Autowired private EventRepository eventRepo;

  @Autowired private SeatRepository seatRepo;

  @Transactional
  public void generateBookings() {
    createBooking(new BookingRequest(
      "TRÙM CUỐI", 
      "Vincom Nguyễn Chí Thanh",
      "2021-05-24",
      "0902209011", "cuong@techmaster.vn",
      "A10,A11"
    ));

    createBooking(new BookingRequest(
      "BÀN TAY DIỆT QUỶ", 
      "Vincom Center Bà Triệu",
      "2021-05-23",
      "0982299018", "long@techmaster.vn",
      "D12,D13,D14"
    ));

    createBooking(new BookingRequest(
      "PALM SPRINGS", 
      "Royal City",
      "2021-05-23",
      "0882299018", "huong@gmail.com",
      "B8,B9"
    ));
  }

  @Transactional(value = TxType.REQUIRES_NEW, rollbackOn = PersistenceException.class)
  public void generateBookingsDuplicateSeats() {
    createBooking(
      "PALM SPRINGS", 
      "Royal City",
      "2021-05-23",
      "0882299029", "dzung@gmail.com",
      "B9,B10" //Trùng ghế B9
    );
  }

  //Cần kiểm tra duplicate
  @Transactional
  public Booking createBooking(BookingRequest bookingRequest) {
    List<Event> events = eventRepo.findByFilmCinemaDate(
      bookingRequest.getFilmTitle(), 
      bookingRequest.getCinema(), 
      bookingRequest.getDate());

    if (events.isEmpty()) return null;
  
    Event event = events.get(0);
    Customer customer = Customer.builder()
    .mobile(bookingRequest.getMobile())
    .email(bookingRequest.getEmail())
    .build();

    em.persist(customer);

    List<Seat> seats = seatRepo.findByRoomIdAndNameIn(event.getRoom().getId(), bookingRequest.getSeats().split(","));
    //Gọi hàm tính chi phí ở đây      

    Booking booking = Booking.builder()
    .event(event)
    .customer(customer)
    .build();    
    
    long totalAmount = 0;

    for (Seat seat: seats) {
      BookingSeat bs = new BookingSeat(booking, seat);
      em.persist(bs);
      switch (seat.getSeatType()) {
        case VIP:
          totalAmount += event.getPrice() * 1.2f;
          break;
        case KING:
          totalAmount += event.getPrice() * 1.5f;
          break;
        default:
          totalAmount += event.getPrice();
      }
    }

    booking.setTotalAmount(totalAmount);
    em.persist(booking);
    return booking;
  }  
}
