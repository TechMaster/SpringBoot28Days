package vn.techmaster.vincinema.security;

public class Authority {
  public static final String ADMIN = "ADMIN";
  public static final String AUTHOR = "AUTHOR";
  public static final String EDITOR = "EDITOR";
  public static final String USER = "USER";

  private Authority() {}

  public static String hasAuthority(String role) {
    return "hasAuthority('" + role + "')";
  }
}
