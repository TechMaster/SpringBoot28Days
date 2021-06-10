package vn.techmaster.vincinema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByUsername(String username);
}