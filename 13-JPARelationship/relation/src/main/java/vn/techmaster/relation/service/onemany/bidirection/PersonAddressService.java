package vn.techmaster.relation.service.onemany.bidirection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.onemany.bidirection.Address;
import vn.techmaster.relation.model.onemany.bidirection.Person;
import vn.techmaster.relation.repository.onemany.bidirection.PersonRepository;

@Service
public class PersonAddressService {
  @Autowired private PersonRepository personRepository;

  @Transactional
  public void generatePersonAddress() {
    Person cuong = new Person("Trịnh Minh Cường");
    cuong.addAddress(new Address("14 ngõ 4 Nguyễn Đình Chiểu"));
    cuong.addAddress(new Address("tầng 12A, Viwaseen Tower, 48 Tố Hữu"));


    Person dzung = new Person("Đoàn Xuân Dũng");
    dzung.addAddress(new Address("Ngách 11, tổ 1, tập thể Bưu Điện"));

    Person johnLennon = new Person("John Lenon");
    johnLennon.addAddress(new Address("Empire State, New York"));

    personRepository.save(johnLennon);
    personRepository.save(cuong);
    personRepository.save(dzung);
    
    //Demo chức năng removeOrphan, xoá johnLennon thì các địa chỉ của John Lennon cũng bị xoá theo.
    personRepository.delete(johnLennon);
  }
}
