package vn.techmaster.relation.model.inheritance.mappedsuperclass;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseProduct {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;
  private String manufacturer;
  public BaseProduct(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
  }
}