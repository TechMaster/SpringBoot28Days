package vn.techmaster.vincinema.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Index;


import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.vincinema.pojo.CinemaPOJO;
@Entity(name = "event")
@Table(name = "event", indexes={
  @Index(name = "idx_date", columnList = "date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {  //Một suất chiếu
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Film film;

  @ManyToOne(fetch = FetchType.EAGER)
  private Room room;

  @JsonGetter(value="cinema")
  public CinemaPOJO getCinema() {
    Cinema cinema = room.getCinema();
    return new CinemaPOJO(cinema.getId(), cinema.getName());
  }

  private String date; //Buộc phải theo định dạng yyyy-MM-dd ví dụ 2021-05-23

  private String beginAt; //suất chiếu bắt đầu lúc mấy giờ HH:mm ví dụ 10:30

  private Integer price; //Giá vé tiêu chuẩn cho loại ghế ngồi Normal

}


