package vn.techmaster.demoquery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="movie")
@Data
@NoArgsConstructor
public class Movie {
  @Id private long id;
  private String title;
  private int year;  
}
