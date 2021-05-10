package vn.techmaster.relation.repository.oneone;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.relation.model.oneone.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
