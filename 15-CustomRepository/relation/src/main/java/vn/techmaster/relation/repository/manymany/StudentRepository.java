package vn.techmaster.relation.repository.manymany;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.relation.model.manymany.separate_primary_key.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByName(String name);
}
