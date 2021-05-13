package vn.techmaster.relation.model.sample;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="person")
//Phần này demo phần đánh index
@Table(name="person", indexes={
  @Index(name = "idx_name", columnList = "name"),
  @Index(name = "idx_job", columnList = "job"),
  @Index(name = "idx_city", columnList = "city")
})
@Data
@NoArgsConstructor
public class Person {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "serial")
  private Long id;
  
  @Column(name="name", nullable = false)
  private String name;
  private String job;

  @NaturalId
  private String email;

  private String gender;
  private String city;
  private Boolean active;
  private int salary;

  @Temporal(TemporalType.DATE)
  private Date birthday;

  @Transient
  private int age;
  public int getAge(){
    Date safeDate = new Date(birthday.getTime());
    LocalDate birthDayInLocalDate = safeDate.toInstant()
    .atZone(ZoneId.systemDefault())
    .toLocalDate();
    return Period.between(birthDayInLocalDate, LocalDate.now()).getYears();
  }
}
