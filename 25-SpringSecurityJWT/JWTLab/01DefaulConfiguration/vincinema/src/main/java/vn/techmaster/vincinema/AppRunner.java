package vn.techmaster.vincinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.techmaster.vincinema.service.FilmService;


@Component
public class AppRunner implements CommandLineRunner{
  @Autowired private FilmService filmService;
  
  @Override
  public void run(String... args) throws Exception {
    filmService.generateSampleFilms();
  }
}