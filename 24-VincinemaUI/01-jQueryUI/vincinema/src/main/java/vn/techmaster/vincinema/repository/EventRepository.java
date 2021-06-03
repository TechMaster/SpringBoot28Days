package vn.techmaster.vincinema.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.techmaster.vincinema.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{        
  List<Event> findAll();
  Optional<Event> findById(Long id);
  List<Event> findByFilmTitle(String title);
  List<Event> findByFilmTitleLikeAndRoomCinemaId(String title, Long cinemaId);
  List<Event> findByDateAndFilmTitleLikeAndRoomCinemaId(String date, String title, Long cinemaId);

  @Query(value ="""
  SELECT e FROM event e 
  INNER JOIN e.room r 
  INNER JOIN r.cinema c 
  WHERE c.name LIKE %:cinema% 
  AND e.date = :date
  """)
  List<Event> findByCinemaDate(
  @Param("cinema") String cinemaName,
  @Param("date") String date);

  @Query(value ="""
  SELECT e FROM event e 
  INNER JOIN e.film f 
  INNER JOIN e.room r 
  INNER JOIN r.cinema c 
  WHERE f.title LIKE %:title% 
  AND c.name LIKE %:cinema% 
  AND e.date = :date
  """)
  List<Event> findByFilmCinemaDate(
    @Param("title") String filmTitle,
    @Param("cinema") String cinemaName,
    @Param("date") String date
  );
}
