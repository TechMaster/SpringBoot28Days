package vn.techmaster.seatbooking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import vn.techmaster.seatbooking.exception.BookingException;
import vn.techmaster.seatbooking.model.BookingSeat;
import vn.techmaster.seatbooking.model.Customer;
import vn.techmaster.seatbooking.model.PaidSeat;
import vn.techmaster.seatbooking.request.BookingRequest;

@Service
@EnableScheduling
public class BookingService {
  HashSet<String> allSeats = new HashSet<>();
  HashMap<String, BookingSeat> bookingSeats = new HashMap<>();
  HashMap<String, BookingSeat> bookingCustomers = new HashMap<>(); //Lưu BookSeat theo key là customer mobile
  HashMap<String, PaidSeat> paidSeats = new HashMap<>();
  public static final long BOOK_TIMEOUT = 20; //chỉ cho phép delay trong 5 giây
  private Semaphore mutex = new Semaphore(1);
  
  public BookingService() {
    // Sinh ra các hàng ghế từ A -> E, mỗi hàng có 10 ghế ban đầu ở trạng thái AVAILABLE
    String[] rows = {"A", "B", "C", "D", "E"};
    for (String row : rows) {
      for (int i = 0; i < 10; i++) {
        allSeats.add(row + i);
      }
    }
  }

  public void processBookingRequest(BookingRequest request) {
    List<String> seatNos = Arrays.asList(request.getSeats().split(","));    

    for (String seatNo : seatNos) {
      
      if (!allSeats.contains(seatNo)) {
        throw new BookingException("Ghế " + seatNo + " không hợp lệ");
      }

      if (bookingSeats.containsKey(seatNo)) {
        throw new BookingException("Ghế " + seatNo + " đã được giữ chỗ. Quý khách chọn chỗ khác hoặc chờ sau 3 phút nữa");
      }

      if (paidSeats.containsKey(seatNo)) {
        throw new BookingException("Ghế " + seatNo + " đã được mua.  Quý khách chọn chỗ khác");
      }
    }
    LocalDateTime bookedTime = LocalDateTime.now();
    for (String seatNo : seatNos) {
      BookingSeat bookingSeat = new BookingSeat(
        seatNo,
        new Customer(request.getMobile()), 
        bookedTime);

      bookingSeats.put(seatNo, bookingSeat);
      bookingCustomers.put(request.getMobile(), bookingSeat);
    }
    
  }
  public Collection<BookingSeat> getAllBooking() {
    return bookingSeats.values();
  }

  @Scheduled(fixedDelay = 100) //lặp lại cứ mỗi 100 milliseconds
  public void scheduleFixedDelayTask() {
    Iterator<BookingSeat> iterator = bookingSeats.values().iterator();

    while (iterator.hasNext()) {
      BookingSeat bs = iterator.next();
      long diff = ChronoUnit.SECONDS.between(bs.getBookedTime(), LocalDateTime.now());
      if (diff > BOOK_TIMEOUT) {
        String mobile = bs.getCustomer().getMobile();
        try {
          mutex.acquire();
          iterator.remove();
          bookingCustomers.remove(mobile);
        } catch (InterruptedException e) {
          
        } finally {
          mutex.release();
        }
        
      }
    }
  }
}
