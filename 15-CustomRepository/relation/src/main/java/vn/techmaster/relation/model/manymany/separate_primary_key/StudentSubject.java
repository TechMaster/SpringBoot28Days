package vn.techmaster.relation.model.manymany.separate_primary_key;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "student_subject")
@Table(name = "student_subject")
@Data
@NoArgsConstructor
public class StudentSubject {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "subject_id")
  private Subject subject;

  private float score; //extra column
  
  public StudentSubject(Student student, Subject subject, float score) {
    this.student = student;
    this.subject = subject;
    this.score = score;
  }
}
