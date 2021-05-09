package vn.techmaster.relation.repository.selfreference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.relation.model.selfreference.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  
}
