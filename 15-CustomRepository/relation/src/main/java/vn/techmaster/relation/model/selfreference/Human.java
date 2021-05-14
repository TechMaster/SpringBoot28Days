package vn.techmaster.relation.model.selfreference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "human")
@Table(name = "human")
@Data
@NoArgsConstructor
public class Human {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Human mother;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Human father;

  @ManyToMany
  @JsonIgnore
  private List<Human> parents = new ArrayList<>();
 
 
  @ManyToMany(mappedBy = "parents")
  @JsonIgnore
  private List<Human> children = new ArrayList<>();

  public Human(String name, Human mother, Human father) {
    this.name = name;
    this.mother = mother;
    this.father = father;
    if (mother != null) parents.add(mother);
    if (father != null) parents.add(father);
  }

  @Transient
  @JsonGetter(value = "parents")
  @JsonInclude(Include.NON_NULL)
  public List<String> getParents() {
    if (parents.isEmpty()) return null;

    List<String> result = new ArrayList<>();
    parents.stream().forEach(human -> result.add(human.getName()));
    return result;
  }

  @Transient
  @JsonGetter(value = "children")
  @JsonInclude(Include.NON_NULL)
  public List<String> getChildren() {   
    if (children.isEmpty()) return null;
    
    List<String> result = new ArrayList<>();
    children.stream().forEach(person -> result.add(person.getName()));
    return result;
  }
}
