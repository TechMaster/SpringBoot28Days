package vn.techmaster.vincinema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.vincinema.model.Cinema;
import vn.techmaster.vincinema.model.Room;
import vn.techmaster.vincinema.model.Seat;
import vn.techmaster.vincinema.repository.CinemaRepository;
import vn.techmaster.vincinema.repository.RoomRepository;

@Service
public class RoomService {
  @PersistenceContext
  private EntityManager em;

  @Autowired private RoomRepository roomRepo;

  @Autowired private CinemaRepository cinemaRepo;
  
  @Transactional
  public void generateRooms() {
    Cinema cgvVinComBaTrieu = cinemaRepo.findByNameContaining("Vincom Center Bà Triệu");
    Cinema cgvVinComRoyalCity = cinemaRepo.findByNameContaining("Vincom Royal City");
    Cinema cgvVinComNguyenChiThanh = cinemaRepo.findByNameContaining("Vincom Nguyễn Chí Thanh");

    Room r01BaTrieu = Room.builder()
    .name("01")
    .cinema(cgvVinComBaTrieu)
    .build();
    r01BaTrieu.generateSeats("A:10,B:10,C:12,D:14,E:16");

    Room r02BaTrieu = Room.builder()
    .name("02")
    .cinema(cgvVinComBaTrieu)
    .build();
    r02BaTrieu.generateSeats("A:12,B:10,C:10,D:12,E:14");

    Room r01Royal = Room.builder()
    .name("01")
    .cinema(cgvVinComRoyalCity)
    .build();
    r01Royal.generateSeats("A:10,B:10,C:12,D:14,E:16");

    Room r02Royal = Room.builder()
    .name("02")
    .cinema(cgvVinComRoyalCity)
    .build();
    r02Royal.generateSeats("A:12,B:12,C:12,D:14,E:16");


    Room r01NguyenChiThanh = Room.builder()
    .name("01")
    .cinema(cgvVinComNguyenChiThanh)
    .build();
    r01NguyenChiThanh.generateSeats("A:12,B:12,C:12,D:14,E:16");

    Room r08NguyenChiThanh = Room.builder()
    .name("08")
    .cinema(cgvVinComNguyenChiThanh)
    .build();
    r08NguyenChiThanh.generateSeats("A:8,B:8,C:10,D:10,E:16");

    em.persist(r01BaTrieu);
    em.persist(r02BaTrieu);
    em.persist(r01Royal);
    em.persist(r02Royal);
    em.persist(r01NguyenChiThanh);
    em.persist(r08NguyenChiThanh);
  }

  List<Seat> getSeats(Long roomID, String seatPattern) {
    Room room = roomRepo.getById(roomID);
  }
}
