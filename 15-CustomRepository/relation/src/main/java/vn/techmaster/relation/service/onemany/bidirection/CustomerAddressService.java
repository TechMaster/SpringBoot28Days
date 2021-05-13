package vn.techmaster.relation.service.onemany.bidirection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.onemany.bidirection.Address;
import vn.techmaster.relation.model.onemany.bidirection.Customer;
import vn.techmaster.relation.repository.onemany.bidirection.CustomerRepository;

@Service
public class CustomerAddressService {
  @Autowired private CustomerRepository customerRepository;

  @Transactional
  public void generateCustomerAddress() {
    Customer cuong = new Customer("Trịnh Minh Cường");
    cuong.addAddress(new Address("14 ngõ 4 Nguyễn Đình Chiểu"));
    cuong.addAddress(new Address("tầng 12A, Viwaseen Tower, 48 Tố Hữu"));


    Customer dzung = new Customer("Đoàn Xuân Dũng");
    dzung.addAddress(new Address("Ngách 11, tổ 1, tập thể Bưu Điện"));

    Customer johnLennon = new Customer("John Lenon");
    johnLennon.addAddress(new Address("Empire State, New York"));

    customerRepository.save(johnLennon);
    customerRepository.save(cuong);
    customerRepository.save(dzung);
    
    //Demo chức năng removeOrphan, xoá johnLennon thì các địa chỉ của John Lennon cũng bị xoá theo.
    customerRepository.delete(johnLennon);
  }
}
