package vn.techmaster.vincinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.techmaster.vincinema.model.Booking;
import vn.techmaster.vincinema.response.BookingInfo;

public interface BookingRepository extends JpaRepository<Booking, Long>{
  @Query("""
  SELECT new vn.techmaster.vincinema.response.BookingInfo(b.id,
  c.mobile, 
  c.email, 
  f.title, 
  cine.name, 
  e.date, 
  e.beginAt, 
  b.seats, 
  b.totalAmount, 
  b.createdOn) 
  FROM booking b 
  INNER JOIN b.customer c 
  INNER JOIN b.event e 
  INNER JOIN e.film f 
  INNER JOIN e.room r 
  INNER JOIN r.cinema cine
  """)
  List<BookingInfo> getBookingInfo();
}
