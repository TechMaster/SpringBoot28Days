package vn.techmaster.vincinema.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "genre")
@Table(name = "genre")
@Data
@NoArgsConstructor
public class Genre {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @JsonBackReference
  @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
  List<Film> films = new ArrayList<>();
}
