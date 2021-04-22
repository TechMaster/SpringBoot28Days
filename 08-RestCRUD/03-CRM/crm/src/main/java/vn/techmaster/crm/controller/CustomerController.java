package vn.techmaster.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.crm.model.Customer;
import vn.techmaster.crm.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customer/v1")
public class CustomerController {
  @Autowired
  private CustomerService customerService;
  
  @GetMapping
  public ResponseEntity<List<Customer>> getCustomers(){
    return ResponseEntity
      .ok()
      .header("onemount", "specialvalue")
      .body(customerService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id){
    return ResponseEntity
      .ok()
      .body(customerService.findById(id));
  }
}