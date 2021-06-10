package vn.techmaster.vincinema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "film")
@Table(name = "film", indexes={
  @Index(name = "idx_title", columnList = "title,description"),
  @Index(name = "idx_director_actor", columnList = "director,actors"),
})

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Film {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 200)
  private String title;

  @Column(nullable = false, length = 2000)
  private String description;

  private int lengthInMinutes; //thời lượng

  @Temporal(TemporalType.DATE)
  private Date beginShowDate; //khởi chiếu

  private Language language;  //ngôn ngữ gốc

  private boolean hasVietSubTitle; //có phụ đề tiếng Việt hay không

  private Rating rating; //giới hạn tuổi

  private String director; //đạo diễn

  private String actors; //danh sách các diễn viên
  
}
