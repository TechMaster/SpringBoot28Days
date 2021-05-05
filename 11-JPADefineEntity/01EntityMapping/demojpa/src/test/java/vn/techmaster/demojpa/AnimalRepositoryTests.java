package vn.techmaster.demojpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.mapping.Animal;
import vn.techmaster.demojpa.repository.AnimalRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@DataJpaTest
@Sql({"/animal.sql"})
public class AnimalRepositoryTests {
  @Autowired
  private AnimalRepository animalRepo;

  @Autowired
  private TestEntityManager testEntityManager;
  
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

  @Test
  @DisplayName("04. Test with TestEntityManager")
  public void insertNewAnimal2() {
    Animal conMeo = new Animal("Mèo Anh");
    Long id = (Long) testEntityManager.persistAndGetId(conMeo);
    assertThat(testEntityManager.find(Animal.class,  id)).isEqualTo(conMeo);
  }

  @Test
  @DisplayName("05. Get EntityManager from TestEntityManager")
  public void getEntityManagerFromTestingEntityManager() {
    EntityManager entityManager = testEntityManager.getEntityManager();
    Query query = entityManager.createNativeQuery("SELECT * FROM animal WHERE id = 1", Animal.class);
    Animal animal = (Animal) query.getSingleResult();
    assertThat(animal.getName()).isEqualTo("Marshbird, brown and yellow");
  }
}
