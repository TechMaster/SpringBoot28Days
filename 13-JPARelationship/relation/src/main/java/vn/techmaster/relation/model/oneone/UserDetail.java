package vn.techmaster.relation.model.oneone;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class UserDetail {
  @Id
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private User user;

  private String job;
  private String address;
  
  public UserDetail(String job, String address) {
    this.job = job;
    this.address = address;
  }
}
