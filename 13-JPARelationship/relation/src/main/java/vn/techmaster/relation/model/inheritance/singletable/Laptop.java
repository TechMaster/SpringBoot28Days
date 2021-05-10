package vn.techmaster.relation.model.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="laptop")
@DiscriminatorValue("Laptop")
@Data
@NoArgsConstructor
public class Laptop extends Electronics {
  private CPUType cpuType;
  private int ramSize;
  private float screenSize;

  public Laptop(String name, String manufacturer, String model, 
  CPUType cpuType, int ramSize, float screenSize) {
    super(name, manufacturer, model);
    this.cpuType = cpuType;
    this.ramSize = ramSize;
    this.screenSize = screenSize;
  }
}