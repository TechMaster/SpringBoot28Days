package vn.techmaster.relation.model.inheritance.joinedtable;

import javax.persistence.Entity;

@Entity(name = "onlinecourse")
public class OnlineCourse extends Course {
  private int numberOfVideos;
}
