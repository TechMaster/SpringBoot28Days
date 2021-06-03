package vn.techmaster.vincinema.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "film")
@Table(name = "film", indexes={
  @Index(name = "idx_title", columnList = "title,description"),
  @Index(name = "idx_director_actor", columnList = "director,actors"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  @ManyToMany
  @JoinTable(
      name = "film_genre",
      joinColumns = @JoinColumn(name = "film_id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  @JsonManagedReference
  @Builder.Default
  private List<Genre> genres = new ArrayList<>();
  
  public void addGenre(Genre genre) {
    genres.add(genre);
    genre.getFilms().add(this);        
  }

  public void removeGenre(Genre genre) {
    genres.remove(genre);
    genre.getFilms().remove(this);
  }

}
