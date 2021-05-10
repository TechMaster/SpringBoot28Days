package vn.techmaster.relation.service.inheritance.tableperclass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.inheritance.tableperclass.Fish;
import vn.techmaster.relation.model.inheritance.tableperclass.FishType;

@Service
public class AnimalService {
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void generateAnimals() {
    Fish caTram = new Fish("Trắm", FishType.FRESH_WATER);
    Fish muc = new Fish("Mực", FishType.SALT_WATER);
    Fish caQua = new Fish("Cá Quả", FishType.BRACKISH_WATER);

    em.persist(caTram);
    em.persist(muc);
    em.persist(caQua);
  }
}
