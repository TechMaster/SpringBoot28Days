package vn.techmaster.demoquery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "car")
@Table(name = "car")
@Data
@NoArgsConstructor
public class Car {
  @Id private long id;
  private String model;
  private String maker;
  private int year;
}
