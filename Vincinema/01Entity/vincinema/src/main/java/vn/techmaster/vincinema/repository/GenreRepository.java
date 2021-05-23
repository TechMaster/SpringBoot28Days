package vn.techmaster.vincinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
  public Genre findByName(String name);
}
