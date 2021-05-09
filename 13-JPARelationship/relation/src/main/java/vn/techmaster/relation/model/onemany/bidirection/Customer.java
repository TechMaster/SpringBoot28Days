package vn.techmaster.relation.model.onemany.bidirection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="customer")
@Table(name="customer")
@Data
@NoArgsConstructor
public class Customer {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public Customer(String name) {
    this.name = name;
  }

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private List<Address> addresses = new ArrayList<>();
  public void addAddress(Address address) {
    address.setCustomer(this);
    addresses.add(address);
  }

}
