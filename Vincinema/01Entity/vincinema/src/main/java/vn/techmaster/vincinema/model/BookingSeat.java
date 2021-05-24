package vn.techmaster.vincinema.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bookingseat")
@Table(name = "bookingseat",
uniqueConstraints={
  @UniqueConstraint(columnNames = {"event_id", "seat_id"})
})

@Data
@NoArgsConstructor
public class BookingSeat {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  private Event event;

  @ManyToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;  

  @ManyToOne
  @JoinColumn(name = "seat_id")
  private Seat seat;  

  public BookingSeat(Booking booking, Seat seat) {
    this.booking = booking;
    this.seat = seat;
    this.event = booking.getEvent();
  }


}
