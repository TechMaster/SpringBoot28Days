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
import vn.techmaster.bank.exception.BankErrorCode;

@Entity(name="alllog")
@Table(name="alllog")
@Data
public class AllLog {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private long fromID;

  private long toID;

  private long amount;

  private BankErrorCode resultCode;

  private String detail;

  private Date createdOn;
  
  public AllLog (long fromID, long toID, Long amount, 
    BankErrorCode resultCode, String detail){
    this.fromID = fromID;
    this.toID = toID;
    this.amount = amount;
    this.resultCode = resultCode;
    this.detail = detail;
    this.createdOn = new Date();
  }
}
