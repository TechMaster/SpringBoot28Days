package vn.techmaster.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.crm.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
  
}
