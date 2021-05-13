package vn.techmaster.relation.model.manymany.separate_primary_key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "student")
@Table(name = "student")
@Data
@NoArgsConstructor
public class Student {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  public Student(String name) {
    this.name = name;
  }

  @OneToMany(mappedBy = "student")
  @JsonIgnore //để tránh bị xuất ra quá nhiều dữ liệu lồng nhau
  private List<StudentSubject> studentSubjects = new ArrayList<>();

  @JsonGetter(value = "subjects")
  @Transient
  public Map<String, Float> getSubjects() {
    Map<String, Float> subjectScore = new HashMap<>();
    studentSubjects.stream().forEach( studentSubject -> {
      subjectScore.put(studentSubject.getSubject().getName(), studentSubject.getScore());
      }    
    );
    return subjectScore;
  }

  
}
