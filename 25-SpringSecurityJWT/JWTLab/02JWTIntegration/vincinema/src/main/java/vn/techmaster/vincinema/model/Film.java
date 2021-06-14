package vn.techmaster.vincinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "film")
@Table(name = "film", indexes={
  @Index(name = "idx_title", columnList = "title,description"),
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
}
