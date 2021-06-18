package vn.techmaster.securityjpa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Table(name = "user")
@Data
@NoArgsConstructor //Phải có không là báo lỗi No default constructor for entity vn.techmaster.securityjpa.model.User
public class User implements UserDetails {
  private static final long serialVersionUID = 6268404888144025944L;

  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;
  private boolean enabled;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
    name = "users_roles", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();
  
  public void addRole(Role role) {
    roles.add(role);
    role.getUsers().add(this);
  }

  public void removeRole(Role role) {
    roles.remove(role);
    role.getUsers().remove(this);
  }

  //--- Constructor --------------------------------
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.enabled = true;
  }


  //-------- Implements các methods của interface UserDetails
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();    
    for (Role role : roles) {
        authorities.add(new SimpleGrantedAuthority(role.getName()));
    }      
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  //---@GET
 //Một User viết nhiều Post
  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private List<Post> posts = new ArrayList<>();
  public void addPost(Post post) {
    posts.add(post);
    post.setUser(this);
  }

  public void removePost(Post post) {
    posts.remove(post);
    post.setUser(null);
  }
}
