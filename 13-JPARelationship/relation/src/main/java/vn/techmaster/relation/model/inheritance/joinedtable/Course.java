package vn.techmaster.relation.model.inheritance.joinedtable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class Course {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)  
  protected Long id;

  protected String title;

  protected Course(String title) {
    this.title = title;
  }
}
