package vn.techmaster.relation.model.onemany.bidirection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "department")
@Table(name = "khoa")
@Data
public class Department {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public Department(String name) {
    this.name = name;
  }

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
  @JoinColumn(name = "khoa_id")
  private List<Professor> professors = new ArrayList<>();
  public void add(Professor professor) {
    professor.setDepartment(this);
    professors.add(professor);
  }

  public void remove(Professor professor) {
    professor.setDepartment(null);
    professors.remove(professor);
  }

  /*
  Nếu không có hàm này, khi department xoá, thì xoá luôn giáo sư
  */
  @PreRemove
  public void preRemove() {
    professors.stream().forEach(p -> p.setDepartment(null));
    professors.clear();
  }
}
