package vn.techmaster.bank.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity(name="transactlog")
@Table(name="transactlog")
@Data
public class TransactLog {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToOne(fetch = FetchType.EAGER)
  private Account accountFrom;
  
  @ManyToOne(fetch = FetchType.EAGER)
  private Account accountTo;

  private long amount;

  private Date createdOn;
  
  public TransactLog (Account accountFrom, Account accountTo, Long amount, Date createdOn){
    this.accountFrom = accountFrom;
    this.accountTo = accountTo;
    this.amount = amount;
    this.createdOn = createdOn;
  }
}
