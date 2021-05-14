package vn.techmaster.relation.model.manymany.noextracolumns;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "article")
@Table(name = "article")
@Data
@NoArgsConstructor
public class Article {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String title;

  public Article(String title) {
    this.title = title;
  }

  @ManyToMany
  @JoinTable(
      name = "article_tag",
      joinColumns = @JoinColumn(name = "article_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  /*@JsonIgnore
  private List<Tag> tags = new ArrayList<>();
  
  public void addTag(Tag tag) {
      tags.add(tag);
      tag.getArticles().add(this);        
  }

  public void removeTag(Tag tag) {
      tags.remove(tag);
      tag.getArticles().remove(this);
  }

  @JsonGetter(value = "tags")
  @Transient
  public List<String> getTags() {
    List<String> result = new ArrayList<>();
    tags.stream().forEach(tag -> result.add(tag.getName()));
    return result;
  }*/

  //Cách làm rất đẹp của Huy, cách này có cả object mà không bị recursive
  @JsonManagedReference
  private List<Tag> tags = new ArrayList<>();
  
  public void addTag(Tag tag) {
      tags.add(tag);
      tag.getArticles().add(this);        
  }

  public void removeTag(Tag tag) {
      tags.remove(tag);
      tag.getArticles().remove(this);
  }
}