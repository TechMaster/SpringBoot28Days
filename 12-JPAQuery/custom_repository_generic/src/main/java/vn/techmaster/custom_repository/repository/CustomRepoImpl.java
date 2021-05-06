package vn.techmaster.custom_repository.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public class CustomRepoImpl<T> implements CustomRepo<T>{
  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public void refresh(T entity) {
    em.refresh(entity);    
  }  
}
