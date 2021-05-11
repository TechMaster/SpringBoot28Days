package vn.techmaster.demoquery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demoquery.model.Movie;
import vn.techmaster.demoquery.repository.MovieRepository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

@DataJpaTest
@Sql({"/movie.sql"})
public class MovieRepositoryTest {
  @Autowired
  private MovieRepository MovieRepo;

  @Test
  public void findAllMovies() {
    List<Movie> movies = MovieRepo.findAll();
    assertThat(movies.size()).isGreaterThan(10);
    assertThat(movies).extracting("title").contains("Eulogy", "Cherry Tree Lane", "Crazy for Christmas");
  }

  @Test
  public void getSortMoviesByYear() {
    List<Movie> movies = MovieRepo.findAllByOrderByYearAsc();
    movies.forEach(System.out::println);
    assertThat(movies).isSortedAccordingTo(Comparator.comparing(Movie::getYear));

  }
  @Test
  public void getSortMoviesByTitleAndYear() {
    Sort sortByTitleAndYear = Sort.by("title", "year");
    List<Movie> movies = MovieRepo.findAll(sortByTitleAndYear);
    movies.forEach(System.out::println);

    assertThat(movies).isSortedAccordingTo(Comparator.comparing(Movie::getTitle).
    thenComparing(Movie::getYear));
  }
  
}
