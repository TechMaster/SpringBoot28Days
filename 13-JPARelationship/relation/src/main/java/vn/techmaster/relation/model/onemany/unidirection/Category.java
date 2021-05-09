package vn.techmaster.relation.model.onemany.unidirection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "category")
@Table(name = "category")
@Data
public class Category {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private String name;

  public Category(String name) {
    this.name = name;
  }
}
