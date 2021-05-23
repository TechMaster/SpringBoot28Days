package vn.techmaster.vincinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
  public City findByName(String name);
}
