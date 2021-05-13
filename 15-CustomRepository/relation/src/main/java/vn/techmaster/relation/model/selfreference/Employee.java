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

@Table(name="employee")
@Entity(name="employee")
@Data
@NoArgsConstructor
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name; 

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Employee manager; //Sếp trực tiếp

  @ManyToMany(mappedBy = "manager")
  @JsonIgnore
  private List<Employee> staffs;

  public Employee(String name, Employee manager) {
    this.name = name;
    this.manager = manager;
  }

  @Transient
  @JsonGetter(value = "manager")
  @JsonInclude(Include.NON_NULL)
  public String getManager() {
    return manager != null ? manager.getName() : null;
  }

  @Transient
  @JsonGetter(value = "staffs")
  @JsonInclude(Include.NON_NULL)
  public List<String> getStaffs() {
    if (staffs.isEmpty()) return null;
    
    List<String> result = new ArrayList<>();
    staffs.stream().forEach(emp -> result.add(emp.getName()));
    return result;
  }
}
