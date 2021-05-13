package vn.techmaster.relation.model.inheritance.mappedsuperclass;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "shoe")
@Table(name = "shoe")
@Data
@NoArgsConstructor
public class Shoe extends BaseProduct{
  private ShoeSize size;
  public Shoe(String name, String manufacturer, ShoeSize size) {
    super(name, manufacturer);
    this.size = size;
  }
}
