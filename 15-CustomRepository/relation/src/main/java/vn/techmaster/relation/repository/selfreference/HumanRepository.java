package vn.techmaster.relation.repository.selfreference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.relation.model.selfreference.Human;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
  
}