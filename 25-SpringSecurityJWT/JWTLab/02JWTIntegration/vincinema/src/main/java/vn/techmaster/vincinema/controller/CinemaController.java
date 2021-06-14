package vn.techmaster.vincinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Cinema;
import vn.techmaster.vincinema.service.CinemaService;

@RestController
@RequestMapping(value = "/api/public/cinema")
public class CinemaController {
  @Autowired private CinemaService cinemaService;
  @GetMapping
  public ResponseEntity<List<Cinema>> findAll() {
    return  ResponseEntity.ok().body(cinemaService.findAll());
  }
}
