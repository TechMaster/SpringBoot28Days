package vn.techmaster.relation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import lombok.extern.slf4j.Slf4j;
import vn.techmaster.relation.model.sample.Person;
import vn.techmaster.relation.repository.sample.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;




@DataJpaTest
@Slf4j
//@Sql({"/person.sql"})

public class PersonRepoTest {
  @Autowired
	private PersonRepository personRepo;	
	
	@Test
	@DisplayName("01. Test transient column age derived from birthday column")
	void checkAge() {
		Person person = personRepo.getOne(1L);
		log.info("person.getAge() = " + person.getAge());
		assertThat(person.getAge() > 0).isTrue();
	}

	@Test
	@DisplayName("02. findTop5ByAge")
	void findTop5BySalary() {
		List<Person> people = personRepo.findTop5ByOrderBySalaryDesc();
		assertThat(people.size()).isEqualTo(5);
	}
}
