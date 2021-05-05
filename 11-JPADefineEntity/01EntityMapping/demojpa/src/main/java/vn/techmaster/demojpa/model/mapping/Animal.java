package vn.techmaster.demojpa.model.mapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal")
@Data
@NoArgsConstructor
public class Animal {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;

  public Animal(String name) {
    this.name = name;
  }
}
