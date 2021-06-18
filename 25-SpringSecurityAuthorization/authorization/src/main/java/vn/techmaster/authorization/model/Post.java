package vn.techmaster.authorization.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "post")
@Table(name = "post")
@Data
@NoArgsConstructor
public class Post {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;  //Tác giả viết post

  @JsonGetter(value = "author")
  public String getUserName() {
    return user.getUsername();
  }

  public Post(String title) {
    this.title = title;
  }
}
