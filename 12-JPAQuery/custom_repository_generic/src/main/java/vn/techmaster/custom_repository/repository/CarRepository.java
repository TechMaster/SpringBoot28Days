package vn.techmaster.custom_repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.custom_repository.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, 
CustomRepo<Car>{
  
}
