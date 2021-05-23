package vn.techmaster.vincinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Room;
import vn.techmaster.vincinema.repository.RoomRepository;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
  @Autowired private RoomRepository roomRepository;

  @GetMapping("/{roomName}/{cinema}")
  public ResponseEntity<Room> findByNameAndCinema(
    @PathVariable("roomName") String roomName, 
    @PathVariable("cinema") String cinema) {
      //return  ResponseEntity.ok().body(roomRepository.findByNameAndCinema("01", "Vincom Center Bà Triệu"));
      return  ResponseEntity.ok().body(roomRepository.findByNameAndCinemaNameContaining(roomName, cinema));
      
    }

  @GetMapping("/cinema/{cinemaId}")
  public ResponseEntity<List<Room>> findByCinema(@PathVariable("cinemaId") Long cinemaId) {
    return  ResponseEntity.ok().body(roomRepository.findByCinemaId(cinemaId));
  }
  
}
