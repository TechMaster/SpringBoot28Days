package vn.techmaster.vincinema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.vincinema.model.Event;
import vn.techmaster.vincinema.model.Film;
import vn.techmaster.vincinema.model.Room;
import vn.techmaster.vincinema.repository.EventRepository;
import vn.techmaster.vincinema.repository.FilmRepository;
import vn.techmaster.vincinema.repository.RoomRepository;

@Service
public class EventService {
  @PersistenceContext private EntityManager em;

  @Autowired private RoomRepository roomRepo;
  @Autowired private FilmRepository filmRepo;
  @Autowired private EventRepository eventRepo;

  @Transactional
  public void generateEvents() {
    Room r01BaTrieu = roomRepo.findByNameAndCinemaNameContaining("01", "Vincom Center Bà Triệu");
    Room r02BaTrieu = roomRepo.findByNameAndCinemaNameContaining("02", "Vincom Center Bà Triệu");
    
    Room r01Royal = roomRepo.findByNameAndCinemaNameContaining("01", "Vincom Royal City");
    Room r02Royal = roomRepo.findByNameAndCinemaNameContaining("02", "Vincom Royal City");

    Room r01NguyenChiThanh = roomRepo.findByNameAndCinemaNameContaining("01", "Vincom Nguyễn Chí Thanh");
    Room r08NguyenChiThanh = roomRepo.findByNameAndCinemaNameContaining("08", "Vincom Nguyễn Chí Thanh");

    Film banTayDietQuy = filmRepo.findByTitle("BÀN TAY DIỆT QUỶ");

    Event eBanTayDietQuyAM = Event.builder()
    .film(banTayDietQuy)
    .room(r01BaTrieu)
    .price(100000)
    .date("2021-05-23")
    .beginAt("10:00")
    .build();

    Event eBanTayDietQuyPM = Event.builder()
    .film(banTayDietQuy)
    .room(r02BaTrieu)
    .price(120000)
    .date("2021-05-22")
    .beginAt("20:00")
    .build();

    em.persist(eBanTayDietQuyAM);
    em.persist(eBanTayDietQuyPM);


    Film palmSpring = filmRepo.findByTitle("PALM SPRINGS: MỞ MẮT THẤY HÔM QUA");
    Event palmSpringPM = Event.builder()
    .film(palmSpring)
    .room(r01Royal)
    .price(150000)
    .date("2021-05-23")
    .beginAt("20:00")
    .build();

    em.persist(palmSpringPM);

    Film trumCuoiSieuDang = filmRepo.findByTitle("TRÙM CUỐI SIÊU ĐẲNG");
    Event trumCuoiSieuDangPM = Event.builder()
    .film(trumCuoiSieuDang)
    .room(r01NguyenChiThanh)
    .price(15000)
    .date("2021-05-24")
    .beginAt("19:00")
    .build();
    em.persist(trumCuoiSieuDangPM);

    Film cucNoHoaCucCung = filmRepo.findByTitle("CỤC NỢ HÓA CỤC CƯNG");
    Event cucNoHoaCucCungPM = Event.builder()
    .film(cucNoHoaCucCung)
    .room(r01BaTrieu)
    .price(15000)
    .date("2021-05-23")
    .beginAt("14:00")
    .build();
    em.persist(cucNoHoaCucCungPM);

    Event cucNoHoaCucCung02 = Event.builder()
    .film(cucNoHoaCucCung)
    .room(r08NguyenChiThanh)
    .price(18000)
    .date("2021-05-23")
    .beginAt("14:00")
    .build();
    em.persist(cucNoHoaCucCung02);
    
  }

  public List<Event> findAll() {
    return eventRepo.findAll();
  }

  public List<Event> findByCinemaDate(String cinemaName, String date) {
    return eventRepo.findByCinemaDate(cinemaName, date);
  }

  public List<Event> findByFilmCinemaDate(String filmTitle, String cinemaName, String date) {
    return eventRepo.findByFilmCinemaDate(filmTitle, cinemaName, date);
  }

}
  

