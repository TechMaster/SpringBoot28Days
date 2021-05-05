package vn.techmaster.demojpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.mapping.Animal;
import vn.techmaster.demojpa.repository.AnimalRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
@DataJpaTest
@Sql({"/animal.sql"})
public class AnimalRepositoryTests {
  @Autowired
  private AnimalRepository animalRepo;
  
  @Test
  @DisplayName("01. Get animal count")
  public void getAnimalCount(){
    assertThat(animalRepo.count()).isGreaterThan(10);    
  }
  
  @Test
  @DisplayName("02. Get animal with id = 5")
  public void get5thAnimal() {
    Optional<Animal> animal = animalRepo.findById(5L);
    if (animal.isPresent()) {
      assertThat(animal.get()).extracting("name").isEqualTo("Whale, long-finned pilot");
    }    
  }

  @Test
  @DisplayName("03. Insert a new animal")
  public void insertNewAnimal() {
    long countBeforeInsert = animalRepo.count();
    Animal conHo = new Animal("Hổ Bengal");
    animalRepo.save(conHo);
    long countAfterInsert = animalRepo.count();
    assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
  }
}
