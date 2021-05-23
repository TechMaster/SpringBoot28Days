package vn.techmaster.vincinema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "seat")
@Table(name = "seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name; //"A1" or "B19"

  @Builder.Default
  private SeatType seatType = SeatType.NORMAL; //Normal, VIP, King

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private Room room;

  @JsonBackReference
  @ManyToMany(mappedBy = "seats", fetch = FetchType.LAZY)
  List<Booking> bookings = new ArrayList<>();
}
