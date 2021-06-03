package vn.techmaster.vincinema.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "booking")
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Event event;

  @ManyToOne(fetch = FetchType.EAGER)
  private Customer customer;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn;

  @Builder.Default
  private boolean isPaid = false;

  private Long totalAmount; //Số tiền cần phải trả

  private String seats; //Lưu danh sách chỗ ngồi cách nhau bằng một dấu phẩy.

  @OneToMany(mappedBy = "booking")
  @JsonIgnore //để tránh bị xuất ra quá nhiều dữ liệu lồng nhau
  @Builder.Default
  private List<BookingSeat> bookingSeats = new ArrayList<>();



  @PrePersist
  private void prePersist() {
    createdOn = new Date();
  }

  
}
