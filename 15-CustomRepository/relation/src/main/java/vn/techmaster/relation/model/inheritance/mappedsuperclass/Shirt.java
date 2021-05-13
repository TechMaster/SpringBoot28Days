package vn.techmaster.relation.model.inheritance.mappedsuperclass;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "shirt")
@Table(name = "shirt")
@Data
@NoArgsConstructor
public class Shirt extends BaseProduct{
  private ClothesSize size;
  private Color color;
  public Shirt(String name, String manufacturer, ClothesSize size, Color color) {
    super(name, manufacturer);
    this.size = size;
    this.color = color;
  }
}
