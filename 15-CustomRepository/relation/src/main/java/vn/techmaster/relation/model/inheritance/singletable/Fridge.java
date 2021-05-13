package vn.techmaster.relation.model.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="fridge")
@DiscriminatorValue("Fridge")
@Data
@NoArgsConstructor

public class Fridge extends Electronics {
  private float volume;
  private Voltage voltage;
  
  public Fridge(String name, String manufacturer, String model,
  float volume, Voltage voltage) {
    super(name, manufacturer, model);
    this.volume = volume;
    this.voltage = voltage;
  }
}
