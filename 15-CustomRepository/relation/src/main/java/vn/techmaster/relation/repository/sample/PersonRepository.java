package vn.techmaster.relation.repository.sample;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.techmaster.relation.model.sample.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
  @Query("SELECT new vn.techmaster.relation.repository.sample.JobCount(p.job, COUNT(*)) " + 
  "FROM person AS p GROUP BY p.job ORDER BY 2 DESC")
  List<JobCount> countByJob();

  List<Person> findTop5ByOrderBySalaryDesc();

  List<Person> findByName(String name);

  List<Person> findByNameIs(String name);

  List<Person> findByNameEquals(String name);

  List<Person> findByNameIsNull();

  List<Person> findByNameNot(String name);

  List<Person> findByNameIsNot(String name);

  List<Person> findByNameStartingWith(String name);

  List<Person> findByNameEndingWith(String name);

  List<Person> findByNameContaining(String name);

  List<Person> findByNameLike(String name);

  List<Person> findBySalaryLessThan(Integer salary);

 
  List<Person> findBySalaryLessThanEqual(Integer salary);

  List<Person> findBySalaryGreaterThan(Integer salary);

  List<Person> findBySalaryGreaterThanEqual(Integer salary);

  List<Person> findBySalaryBetween(Integer startSalary, Integer endSalary);

  List<Person> findByBirthdayAfter(Date birthday);

  List<Person> findByBirthdayBefore(Date birthday);

  List<Person> findByActiveTrue();

  List<Person> findByActiveFalse();

  List<Person> findByNameOrBirthday(String name, Date birthday);

  List<Person> findByNameOrBirthdayAndActive(String name, Date birthday, Boolean active);

  List<Person> findByNameOrderByName(String name);

  List<Person> findByNameOrderByNameDesc(String name);
  
}
