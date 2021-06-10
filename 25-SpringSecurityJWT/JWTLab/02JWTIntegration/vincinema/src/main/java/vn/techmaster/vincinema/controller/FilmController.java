package vn.techmaster.vincinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Film;
import vn.techmaster.vincinema.service.FilmService;

@RestController
@RequestMapping(value = "/api/film")
public class FilmController {
  @Autowired private FilmService filmService;
  @GetMapping()
  public ResponseEntity<List<Film>> findAll() {
    return ResponseEntity.ok().body(filmService.findAll());
  }
  
}