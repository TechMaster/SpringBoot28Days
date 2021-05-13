package vn.techmaster.relation.model.inheritance.tableperclass;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Fish")
@Data
@NoArgsConstructor
public class Fish extends Animal{
  private FishType fishType;

  public Fish(String name, FishType fishType) {
    super(name);
    this.fishType = fishType;
  }
}