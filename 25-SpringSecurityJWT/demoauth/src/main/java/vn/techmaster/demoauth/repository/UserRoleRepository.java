package vn.techmaster.demoauth.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.techmaster.demoauth.entity.CustomUser;

@Service
public class UserRoleRepository {

  static Map<String, CustomUser> DB_BASED_USER_MAPPING;

  static {
    DB_BASED_USER_MAPPING = new LinkedHashMap<>();
    DB_BASED_USER_MAPPING.put("alice",
        new CustomUser("alice", "123", getGrantedAuthorities("ROLE_USER", "ROLE_VIEWER"), "alice"));
    DB_BASED_USER_MAPPING.put("bob",
        new CustomUser("bob", "123", getGrantedAuthorities("ROLE_EDITOR", "ROLE_ADMIN"), "bob"));
    DB_BASED_USER_MAPPING.put("tom",
        new CustomUser("tom", "123", getGrantedAuthorities("ROLE_USER", "ROLE_REVIEWER"), "tom"));
  }

  private static List<GrantedAuthority> getGrantedAuthorities(String... roles) {
    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
    }
    return authorities;
  }

  public CustomUser loadUserByUserName(String username) {
    if (DB_BASED_USER_MAPPING.containsKey(username)) {
      return DB_BASED_USER_MAPPING.get(username);
    }
    throw new UsernameNotFoundException("User " + username + " cannot be found");
  }

  public boolean isValidUsername(String username) {
    return DB_BASED_USER_MAPPING.containsKey(username);
  }

  public boolean isValidRole(String roleName) {
    return roleName.startsWith("ROLE_");
  }

  public List<String> getAllUsernames() {
    List<String> usernames = new ArrayList<>();
    usernames.add("alice");
    usernames.add("bob");
    usernames.add("tom");
    return usernames;
  }

}