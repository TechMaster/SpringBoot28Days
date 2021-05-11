package vn.techmaster.custom_repository.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="car")
@Table(name="car")
@Data
@NoArgsConstructor
public class Car {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String model;
  private String manufacturer;
}
