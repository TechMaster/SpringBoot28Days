package vn.techmaster.securityjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.securityjpa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  /*@Query("SELECT u FROM user u WHERE u.username = :username")
  public User getUserByUsername(@Param("username") String username);*/
  
  public Optional<User> findByUsername(String username);
}