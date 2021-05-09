package vn.techmaster.relation.model.onemany.unidirection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  public Product(String name, Category category) {
    this.name = name;
    this.category = category;
  }
}
