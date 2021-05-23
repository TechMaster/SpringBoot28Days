package vn.techmaster.vincinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
  
}
