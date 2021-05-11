package vn.techmaster.custom_repository.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import vn.techmaster.custom_repository.model.Person;

public class PersonRepositoryImpl implements PersonRepositoryCustom{
  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public void refresh(Person person) {
    em.refresh(person);   
  }
  
}
