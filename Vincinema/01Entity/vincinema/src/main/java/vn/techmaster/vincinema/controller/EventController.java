package vn.techmaster.vincinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Event;
import vn.techmaster.vincinema.service.EventService;

@RestController
@RequestMapping(value = "/event")
public class EventController {
  @Autowired private EventService eventService;

  @GetMapping()
  public ResponseEntity<List<Event>> findAll() {
    return ResponseEntity.ok().body(eventService.findAll());
  }
  /*
  http://localhost:8080/event/find?film=B%C3%80N%20TAY%20DI%E1%BB%86T%20QU%E1%BB%B6&cinema=B%C3%A0%20Tri%E1%BB%87u&date=2021-05-23
  */
  @GetMapping("/find")
  public ResponseEntity<List<Event>> findByFilmCinemaDate(
      @RequestParam(value = "film", required = false) String filmTitle,
      @RequestParam(value = "cinema") String cinemaName,
      @RequestParam(value = "date") String date
  ) {
    if (filmTitle != null) {
      return ResponseEntity.ok().body(eventService.findByFilmCinemaDate(filmTitle, cinemaName, date));
    } else {
      return ResponseEntity.ok().body(eventService.findByCinemaDate(cinemaName, date));
    }
  }
}
