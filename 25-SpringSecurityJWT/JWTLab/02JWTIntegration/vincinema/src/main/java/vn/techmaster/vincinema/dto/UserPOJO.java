package vn.techmaster.vincinema.dto;

public class UserPOJO {
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  private String username;
  public UserPOJO(String username) {
    this.username = username;
  }
}
