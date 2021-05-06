package vn.techmaster.custom_repository.repository;

public interface CustomRepo<T>{  
  void refresh(T entity);  
}