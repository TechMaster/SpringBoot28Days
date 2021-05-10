package vn.techmaster.relation.service.selfreference;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.selfreference.Person;
import vn.techmaster.relation.repository.selfreference.PersonRepository;

@Service
public class FamilyService {
  @PersistenceContext
  private EntityManager em;

  @Autowired private PersonRepository personRepository;

  @Transactional
  public void generateFamilyTree() {
    Person p1 = new Person("Bob", null, null);
    Person p2 = new Person("Alice", null, null);
    Person p3 = new Person("Bob - Alice Joan", p1, p2);
    Person p4 = new Person("Bob - Alice Greenwood", p1, p2);

    Person p5 = new Person("Cate", null, null);
    Person p6 = new Person("Kane", null, null);    
    Person p7 = new Person("Joan - Kane James", p3, p6);
    Person p8 = new Person("Cate - Kane Anna", p5, p6);
    Person p9 = new Person("Cate - Kane Roberto", p5, p6);


    em.persist(p1); em.persist(p2); em.persist(p3); em.persist(p4);
    em.persist(p5); em.persist(p6); em.persist(p7); em.persist(p8);
    em.persist(p9);
    em.flush();
  }

  public List<Person> getAll() {
    return personRepository.findAll();
  }

}
