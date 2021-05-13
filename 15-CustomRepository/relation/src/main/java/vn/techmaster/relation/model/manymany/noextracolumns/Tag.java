package vn.techmaster.relation.model.manymany.noextracolumns;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tag")
@Table(name = "tag")
@Data
@NoArgsConstructor
public class Tag {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public Tag(String name) {
    this.name = name;
  }
  @JsonBackReference
  @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
  List<Article> articles = new ArrayList<>();
}
