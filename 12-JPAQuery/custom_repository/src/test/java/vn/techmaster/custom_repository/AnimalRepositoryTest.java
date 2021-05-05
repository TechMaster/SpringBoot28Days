package vn.techmaster.custom_repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.custom_repository.model.Person;
import vn.techmaster.custom_repository.repository.PersonRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AnimalRepositoryTest {
	@Autowired
	PersonRepository personRepo;

	@Test
	@DisplayName("Test refresh method")
	void testRefresh() {
		Person person = new Person();
		person.setName("Cường");
		personRepo.save(person);

		personRepo.flush();
		person.setName("Long");
		personRepo.refresh(person);
		assertThat(person.getName()).isEqualTo("Cường");
	}

}
