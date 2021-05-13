package vn.techmaster.relation.service.selfreference;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.selfreference.Human;
import vn.techmaster.relation.repository.selfreference.HumanRepository;

@Service
public class FamilyService {
  @PersistenceContext
  private EntityManager em;

  @Autowired private HumanRepository personRepository;

  @Transactional
  public void generateFamilyTree() {
    Human p1 = new Human("Bob", null, null);
    Human p2 = new Human("Alice", null, null);
    Human p3 = new Human("Bob - Alice Joan", p1, p2);
    Human p4 = new Human("Bob - Alice Greenwood", p1, p2);

    Human p5 = new Human("Cate", null, null);
    Human p6 = new Human("Kane", null, null);    
    Human p7 = new Human("Joan - Kane James", p3, p6);
    Human p8 = new Human("Cate - Kane Anna", p5, p6);
    Human p9 = new Human("Cate - Kane Roberto", p5, p6);


    em.persist(p1); em.persist(p2); em.persist(p3); em.persist(p4);
    em.persist(p5); em.persist(p6); em.persist(p7); em.persist(p8);
    em.persist(p9);
    em.flush();
  }

  public List<Human> getAll() {
    return personRepository.findAll();
  }

}
