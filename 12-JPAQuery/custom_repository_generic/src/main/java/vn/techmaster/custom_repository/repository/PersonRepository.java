package vn.techmaster.custom_repository.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.custom_repository.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, 
                                          CustomRepo<Person>{
  Optional<Person> findByName(String name);
}