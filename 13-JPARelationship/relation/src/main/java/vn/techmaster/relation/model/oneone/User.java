package vn.techmaster.relation.model.oneone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="user")
@Entity(name="user")
@Data
@NoArgsConstructor
public class User {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 200)
  private String fullname;

  @NaturalId
  private String email;

  public User(String fullname, String email) {
    this.fullname = fullname;
    this.email = email;
  }

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @PrimaryKeyJoinColumn
  private UserDetail userDetail;

  public void setUserDetail(UserDetail userDetail) {
    userDetail.setUser(this);
    this.userDetail = userDetail;
  }

  
}