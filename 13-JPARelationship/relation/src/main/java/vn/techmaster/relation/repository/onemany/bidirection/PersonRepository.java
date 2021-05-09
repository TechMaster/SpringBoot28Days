package vn.techmaster.relation.repository.onemany.bidirection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.relation.model.onemany.bidirection.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
  
}
