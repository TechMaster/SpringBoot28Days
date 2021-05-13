package vn.techmaster.relation.model.inheritance.tableperclass;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "reptile")
@Data
@NoArgsConstructor
public class Reptile extends Animal {
  private ReptileType type;

  public Reptile(String name, ReptileType type) {
    super(name);
    this.type = type;
  }
}
