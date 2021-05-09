package vn.techmaster.relation.model.onemany.bidirection;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "post")
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  @Column(length = 5000)
  private String content;

  @Embedded
  private Audit audit = new Audit();

  public Post(String title, String content) {
    this.title = title;
    this.content = content;
  }

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "post_id")
  private List<Comment> comments = new ArrayList<>();

  public void addComment(Comment comment) {
    comment.setPost(this);
    comments.add(comment);    
  }

  public void removeComment(Comment comment) {
    comment.setPost(null);
    comments.remove(comment);   
  }
}