package vn.techmaster.custom_repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.custom_repository.model.Car;
import vn.techmaster.custom_repository.model.Person;
import vn.techmaster.custom_repository.repository.CarRepository;
import vn.techmaster.custom_repository.repository.PersonRepository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
class RepositoryTest {
	@Autowired
	PersonRepository personRepo;

	@Autowired
	CarRepository carRepo;

	@Test
	@DisplayName("Test refresh method in PersonRepository")
	void testRefreshInPersonRepo() {
		Person person = new Person();
		person.setName("Cường");
		personRepo.save(person);

		personRepo.flush();
		person.setName("Long");
		personRepo.refresh(person);
		assertThat(person.getName()).isEqualTo("Cường");

		Optional<Person> oPerson = personRepo.findByName("Cường");
		assertThat(oPerson).isNotEmpty();
	}

	@Test
	@DisplayName("Test refresh method in CarRepository")
	void testRefreshInCarRepo() {
		Car car = new Car();
		car.setModel("Land Cruiser");
		car.setManufacturer("Toyota");
		carRepo.save(car);

		carRepo.flush();
		car.setModel("Pajero");
		car.setManufacturer("Mitsubishi");
		carRepo.refresh(car);
		assertThat(car.getModel()).isEqualTo("Land Cruiser");
		assertThat(car.getManufacturer()).isEqualTo("Toyota");
	}

}
