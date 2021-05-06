package vn.techmaster.custom_repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.custom_repository.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>, 
CustomRepo<Car>{
  
}
