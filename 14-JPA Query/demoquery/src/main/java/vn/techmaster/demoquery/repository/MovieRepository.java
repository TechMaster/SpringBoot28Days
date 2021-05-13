package vn.techmaster.demoquery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.demoquery.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  public List<Movie> findAllByOrderByYearAsc();
  public List<Movie> findAllByOrderByTitleAsc();
}
