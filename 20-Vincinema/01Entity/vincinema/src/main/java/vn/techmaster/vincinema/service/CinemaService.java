package vn.techmaster.vincinema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.vincinema.model.Cinema;
import vn.techmaster.vincinema.model.City;
import vn.techmaster.vincinema.repository.CinemaRepository;
import vn.techmaster.vincinema.repository.CityRepository;

@Service
public class CinemaService {
  @PersistenceContext
  private EntityManager em;

  @Autowired private CityRepository cityRepo;

  @Autowired private CinemaRepository cinemaRepo;

  @Transactional
  public void generateCinema(){
    City hanoi = cityRepo.findByName("Hà nội");

    Cinema cgvVinComBaTrieu = Cinema.builder()
    .name("CGV Vincom Center Bà Triệu")
    .address("Tầng 6, Toà nhà VinCom Center Hà Nội 191 đường Bà Triệu Quận Hai Bà Trưng Hà Nội")
    .city(hanoi)
    .build();


    Cinema cgvVinComNguyenChiThanh = Cinema.builder()
    .name("CGV Vincom Nguyễn Chí Thanh")
    .address("Số 54A Nguyễn Chí Thanh, Phường Láng Thượng, Quận Đống Đa, Hà Nội")
    .city(hanoi)
    .build();

    Cinema cgvVinComRoyalCity = Cinema.builder()
    .name("CGV Vincom Royal City")
    .address("Tầng B2- Khu R4, TTTM Vincom Mega Mall Royal City, 72A Nguyễn Trãi, Thanh Xuân, Hà Nội")
    .city(hanoi)
    .build();

    em.persist(cgvVinComBaTrieu);
    em.persist(cgvVinComNguyenChiThanh);
    em.persist(cgvVinComRoyalCity);
  }

  public List<Cinema> findAll() {
    return cinemaRepo.findAll();
  }
}
