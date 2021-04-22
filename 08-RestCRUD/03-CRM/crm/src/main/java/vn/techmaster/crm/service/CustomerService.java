package vn.techmaster.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vn.techmaster.crm.exception.CRMException;
import vn.techmaster.crm.model.Customer;
import vn.techmaster.crm.repository.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  static final String CUSTOMER_ID_NOT_EXIST = "Customer id %d doest not exist";

  public List<Customer> getAll(){
    return customerRepository.findAll();
  }

  public Customer findById(long id){
    Optional<Customer> customer = customerRepository.findById(id);
    if (customer.isPresent()) {
      return customer.get();
    } else {
      throw new CRMException("Cannot find customer",
        new Throwable(String.format(CUSTOMER_ID_NOT_EXIST, id)), 
        HttpStatus.NOT_FOUND);      
    }
    
  }
}
