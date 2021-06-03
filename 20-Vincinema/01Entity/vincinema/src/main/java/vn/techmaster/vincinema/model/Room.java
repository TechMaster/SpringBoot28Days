package vn.techmaster.vincinema.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "room")
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)  
  @JsonBackReference
  private Cinema cinema; //Mỗi phòng chiếu phải thuộc một rạp xác định

 /* @JsonGetter(value = "cinema")
  public String getCinema() {
    return cinema.getName();
  }*/
  

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "room_id")
  @Builder.Default
  @JsonIgnore
  private List<Seat> seats = new ArrayList<>();

  /* rowPattern = "A:10,B:10,C:12,D:14,E:16"
  Hàng A có 10 chỗ
  Hàng B có 10 chỗ
  Hàng C có 12 chỗ
  */
  public void generateSeats(String rowPattern) {
    String[] rows = rowPattern.split(",", 0);
    for (int i = 0; i < rows.length; i++) {
      String row = rows[i];
      String rowName = row.substring(0, 1);
      String numberSeatStr = row.substring(2);
      int numberSeatInRow = Integer.parseInt(numberSeatStr);
      for (int j = 1; j <= numberSeatInRow; j++) {
        Seat seat = Seat.builder()
        .name(rowName + j)
        .room(this)
        .build();
        seats.add(seat);
      }
    }
  }
}
