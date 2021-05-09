package vn.techmaster.relation.model.onemany.bidirection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "address")
@Table(name = "address")
@Data
public class Address {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String detail;
  public Address(String detail) {
    this.detail = detail;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  private Person person;
}
