package vn.techmaster.crm.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vn.techmaster.crm.exception.CRMException;
import vn.techmaster.crm.mapper.CustomerMapper;
import vn.techmaster.crm.model.Customer;
import vn.techmaster.crm.model.CustomerPOJO;
import vn.techmaster.crm.repository.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  private Validator validator;

  static final String CUSTOMER_ID_NOT_EXIST = "Customer id %d doest not exist";

  public CustomerService() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

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


  private void validateCustomer(CustomerPOJO customer) {
    Set<ConstraintViolation<CustomerPOJO>> constraintViolations = validator.validate(customer);
    if (!constraintViolations.isEmpty()) {
      throw new CRMException("New customer violates constrains", 
      new Throwable(
        constraintViolations
        .stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(", "))),
      HttpStatus.BAD_REQUEST);
    }
  }  

  public Customer save(CustomerPOJO customerPOJO) {
    validateCustomer(customerPOJO);
    //Cách cổ điền dùng khi số lượng trường ít
    //Customer newCustomer = new Customer(customer.fullname, customer.email, customer.mobile);

    //Sử dụng MapStruct
    Customer newCustomer = CustomerMapper.INSTANCE.pojoToCustomer(customerPOJO);
    return customerRepository.save(newCustomer);
  }
}
