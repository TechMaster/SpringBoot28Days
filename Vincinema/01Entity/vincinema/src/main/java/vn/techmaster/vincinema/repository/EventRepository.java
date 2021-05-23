package vn.techmaster.vincinema.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.techmaster.vincinema.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
  List<Event> findAll();

  List<Event> findByFilmTitle(String title);

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
