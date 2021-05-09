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

@Entity(name = "person")
@Table(name = "person")
@Data
@NoArgsConstructor
public class Person {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Person mother;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Person father;

  @ManyToMany
  @JsonIgnore
  private List<Person> parents = new ArrayList<>();
 
 
  @ManyToMany(mappedBy = "parents")
  @JsonIgnore
  private List<Person> children = new ArrayList<>();

  public Person(String name, Person mother, Person father) {
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
    parents.stream().forEach(person -> result.add(person.getName()));
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
