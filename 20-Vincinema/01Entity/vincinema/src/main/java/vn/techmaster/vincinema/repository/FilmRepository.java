package vn.techmaster.vincinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
  Film findByTitle(String title);
}
