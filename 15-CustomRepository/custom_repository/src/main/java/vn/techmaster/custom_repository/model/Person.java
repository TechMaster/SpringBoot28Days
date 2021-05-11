package vn.techmaster.custom_repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="person")
@Table(name="person")
@Data
@NoArgsConstructor
public class Person {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
}
