package vn.techmaster.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "customer")
@Table(name = "customer")
public class Customer {
  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String fullname;

  private String email;

  private String mobile;

  public Customer(String fullname, String email, String mobile) {
    this.fullname = fullname;
    this.email = email;
    this.mobile = mobile;
  }
}
