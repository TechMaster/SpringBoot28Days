package vn.techmaster.relation.model.inheritance.joinedtable;

import javax.persistence.Entity;

import lombok.Data;

@Entity(name = "onlinecourse")
@Data
public class OnlineCourse extends Course {
  private int numberOfVideos;
}
