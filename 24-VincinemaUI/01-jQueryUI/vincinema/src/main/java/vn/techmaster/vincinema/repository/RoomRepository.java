package vn.techmaster.vincinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.techmaster.vincinema.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
  @Query("SELECT r FROM room r WHERE r.name =:name AND r.cinema.name LIKE '%:cinema%'")  
  Room findByNameAndCinema(@Param("name") String name, @Param("cinema") String cinema);

  List<Room> findByCinemaName(String name);

  List<Room> findByCinemaId(Long cinemaId);

  Room findByNameAndCinemaNameContaining(String name, String cinema);

}  
