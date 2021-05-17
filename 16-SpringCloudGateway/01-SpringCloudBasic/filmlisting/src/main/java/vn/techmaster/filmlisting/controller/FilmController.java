package vn.techmaster.filmlisting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.filmlisting.model.Film;

@RestController
@RequestMapping(value = "/film")
public class FilmController {
  @GetMapping(value = "/films")
  public ResponseEntity<List<Film>> getAllFilms() {
    List<Film> films = new ArrayList<>();
    films.add(new Film("Tom & Jerry", "Tom, Jerry, Snoop"));
    films.add(new Film("Money Heist", "Professor, Berlin, Tokyo, Rio, Nairobi, Helsinki"));
    films.add(new Film("Làng Vũ Đại ngày ấy", "Chí Phèo, Thị Nở, Bá Kiến, ông giáo"));

    return ResponseEntity.ok().body(films);
  }
}
