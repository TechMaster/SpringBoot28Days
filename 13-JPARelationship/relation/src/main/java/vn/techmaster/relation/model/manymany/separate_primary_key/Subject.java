package vn.techmaster.relation.model.manymany.separate_primary_key;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "subject")
@Table(name = "subject")
@Data
@NoArgsConstructor
public class Subject {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  public Subject(String name) {
    this.name = name;
  }

  @OneToMany(mappedBy = "subject")
  private List<StudentSubject> studentSubjects = new ArrayList<>();

}
