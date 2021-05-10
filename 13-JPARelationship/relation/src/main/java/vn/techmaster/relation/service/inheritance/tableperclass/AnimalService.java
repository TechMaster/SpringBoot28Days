package vn.techmaster.relation.service.inheritance.tableperclass;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.inheritance.tableperclass.Animal;
import vn.techmaster.relation.model.inheritance.tableperclass.EatType;
import vn.techmaster.relation.model.inheritance.tableperclass.Fish;
import vn.techmaster.relation.model.inheritance.tableperclass.FishType;
import vn.techmaster.relation.model.inheritance.tableperclass.Mammal;
import vn.techmaster.relation.model.inheritance.tableperclass.Reptile;
import vn.techmaster.relation.model.inheritance.tableperclass.ReptileType;

@Service
public class AnimalService {
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void generateAnimals() {
    Fish caTram = new Fish("Trắm", FishType.FRESH_WATER);
    Fish muc = new Fish("Mực", FishType.SALT_WATER);
    Fish caQua = new Fish("Cá Quả", FishType.BRACKISH_WATER);

    Mammal tiger = new Mammal("Hổ Bengal", EatType.CARNIVO);
    Mammal buffalo = new Mammal("Trâu", EatType.HERBIVO);

    Reptile cobra = new Reptile("Rắn hổ mang", ReptileType.SNAKE);
    Reptile lizard = new Reptile("Thằn lằn đất", ReptileType.LIZARD);
    Reptile africaCorocodile = new Reptile("Cá sấu châu Phi", ReptileType.CROCODILE);

    em.persist(caTram);
    em.persist(muc);
    em.persist(caQua);
    em.persist(tiger);
    em.persist(buffalo);
    em.persist(cobra);
    em.persist(lizard);
    em.persist(africaCorocodile);
  }

  public List<Animal> getAllAnimals() {
    TypedQuery<Animal> queryAnimal = em.createQuery("SELECT a FROM animal as a", Animal.class);
    return queryAnimal.getResultList();
  }
}
