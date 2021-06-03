package vn.techmaster.vincinema.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.vincinema.model.Cinema;
import vn.techmaster.vincinema.model.Room;
import vn.techmaster.vincinema.repository.CinemaRepository;

@Service
public class RoomService {
  @PersistenceContext
  private EntityManager em;

  @Autowired private CinemaRepository cinemaRepo;
  
  @Transactional
  public void generateRooms() {
    Cinema cgvVinComBaTrieu = cinemaRepo.findByNameContaining("Vincom Center Bà Triệu");
    Cinema cgvVinComRoyalCity = cinemaRepo.findByNameContaining("Vincom Royal City");
    Cinema cgvVinComNguyenChiThanh = cinemaRepo.findByNameContaining("Vincom Nguyễn Chí Thanh");

    Room r01BaTrieu = Room.builder()
    .name("01")
    .cinema(cgvVinComBaTrieu)
    .rowPattern("A:10,B:10,C:12,D:14,E:16")
    .build()
    .generateSeats();

    Room r02BaTrieu = Room.builder()
    .name("02")
    .cinema(cgvVinComBaTrieu)
    .rowPattern("A:12,B:10,C:10,D:12,E:14")
    .build()
    .generateSeats();

    Room r01Royal = Room.builder()
    .name("01")
    .cinema(cgvVinComRoyalCity)
    .rowPattern("A:10,B:10,C:12,D:14,E:16")
    .build()
    .generateSeats();

    Room r02Royal = Room.builder()
    .name("02")
    .cinema(cgvVinComRoyalCity)
    .rowPattern("A:12,B:12,C:12,D:14,E:16")
    .build()
    .generateSeats();


    Room r01NguyenChiThanh = Room.builder()
    .name("01")
    .cinema(cgvVinComNguyenChiThanh)
    .rowPattern("A:12,B:12,C:12,D:14,E:16")
    .build()
    .generateSeats();

    Room r08NguyenChiThanh = Room.builder()
    .name("08")
    .cinema(cgvVinComNguyenChiThanh)
    .rowPattern("A:8,B:8,C:10,D:10,E:16")
    .build()
    .generateSeats();

    em.persist(r01BaTrieu);
    em.persist(r02BaTrieu);
    em.persist(r01Royal);
    em.persist(r02Royal);
    em.persist(r01NguyenChiThanh);
    em.persist(r08NguyenChiThanh);
  }

  

}
