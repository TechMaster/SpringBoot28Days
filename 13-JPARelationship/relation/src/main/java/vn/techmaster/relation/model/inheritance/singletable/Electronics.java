package vn.techmaster.relation.model.inheritance.singletable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="electronics")
@Table(name="electronics")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor

public class Electronics {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String manufacturer;

  private String model;

  public Electronics(String name, String manufacturer, String model) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.model = model;
  }
}
