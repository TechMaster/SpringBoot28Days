package vn.techmaster.relation.model.inheritance.tableperclass;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Mammal")
@Data
@NoArgsConstructor
public class Mammal extends Animal {
  private EatType eatType;
  
  public Mammal(String name, EatType eatType) {
    super(name);
    this.eatType = eatType;
  }
}
