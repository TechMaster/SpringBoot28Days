package vn.techmaster.demoquery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.techmaster.demoquery.model.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
  List<Car> findByModel(String model);
  Optional<Car> findById(Long id);
  List<Car> findByOrderByYearAsc();
  Optional<Car> findByModelAndYear(String model, int year);

  @Query("SELECT c FROM car AS c WHERE c.year=2009")
  List<Car> listCarIn2009();

  @Query("SELECT c FROM car AS c WHERE c.year=:year")
  List<Car> listCarInYear(@Param("year") int year);

  // Phải ghi rõ domain, package của kiểu trả về vn.techmaster.demojpa.model.mapping.MakerCount
  @Query("SELECT new vn.techmaster.demoquery.repository.MakerCount(c.maker, COUNT(*)) " + 
  "FROM car AS c GROUP BY c.maker ORDER BY c.maker ASC")
  List<MakerCount> countByMaker();

  @Query("SELECT new vn.techmaster.demoquery.repository.MakerCount(c.maker, COUNT(*)) " + 
  "FROM car AS c GROUP BY c.maker ORDER BY COUNT(*) DESC")
  List<MakerCount> topCarMaker(Pageable pageable);
  //Chú ý PSQL không hỗ trợ cú pháp SELECT TOP hay LIMIT, thay vào đó phải truyền vào Pageable pageable

}

