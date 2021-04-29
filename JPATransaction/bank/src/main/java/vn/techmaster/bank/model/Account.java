package vn.techmaster.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name="account")
@Entity(name="account")
@Data
@NoArgsConstructor
public class Account {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String owner;
  private Long balance;
  private AccountState state;
  public Account(String owner, Long balance) {
    this.owner = owner;
    this.balance = balance;
    this.state = AccountState.ACTIVE;
  }
}
