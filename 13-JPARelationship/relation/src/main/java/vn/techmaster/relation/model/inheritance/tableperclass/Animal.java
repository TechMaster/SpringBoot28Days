package vn.techmaster.relation.model.inheritance.tableperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "animal")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor

public abstract class Animal {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  protected Animal(String name) {
    this.name = name;
  }
}
