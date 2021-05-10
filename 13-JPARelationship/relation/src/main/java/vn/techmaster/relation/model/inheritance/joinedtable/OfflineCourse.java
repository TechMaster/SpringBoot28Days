package vn.techmaster.relation.model.inheritance.joinedtable;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "offlinecourse")
@Data
@NoArgsConstructor
public class OfflineCourse extends Course {
  private int numberOfLessons;
  private float totalHours;

  public OfflineCourse(String title, int numberOfLessons, float totalHours) {
    super(title);
    this.numberOfLessons = numberOfLessons;
    this.totalHours = totalHours;
  }
}
