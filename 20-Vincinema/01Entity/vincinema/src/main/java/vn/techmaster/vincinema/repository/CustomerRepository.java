package vn.techmaster.vincinema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.vincinema.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
  Optional<Customer> findByMobile(String mobile);
}
