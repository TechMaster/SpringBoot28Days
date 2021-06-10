package vn.techmaster.vincinema.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);
}
