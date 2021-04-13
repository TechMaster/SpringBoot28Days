package vn.techmaster.bmiservice.model;

public class Doctor {
  private int id;
  private String name;
  private String skills;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSkills() {
    return skills;
  }
  public void setSkills(String skills) {
    this.skills = skills;
  }
  public Doctor(int id, String name, String skills) {
    this.id = id;
    this.name = name;
    this.skills = skills;
  }
  
}
