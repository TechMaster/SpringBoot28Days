package vn.techmaster.vincinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
  public Cinema findByNameContaining(String name);
}
