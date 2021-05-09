package vn.techmaster.relation.service.selfreference;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.selfreference.Employee;
import vn.techmaster.relation.repository.selfreference.EmployeeRepository;

@Service
public class EmployeeService {
  @PersistenceContext
  private EntityManager em;

  @Autowired private EmployeeRepository employeeRepository;
  
  @Transactional
  public void generateEmployee() {
    Employee billGates = new Employee("Bill Gates", null);

    Employee steveBallmer = new Employee("Steve Ballmer", billGates);
    Employee satyaNadella = new Employee("Satya Nadella", billGates);

    Employee john = new Employee("John", satyaNadella);
    Employee bob = new Employee("Bob", john);
    Employee alice = new Employee("Alice", john);
    Employee james = new Employee("James", alice);

    em.persist(billGates); em.persist(steveBallmer); em.persist(satyaNadella);
    em.persist(john); em.persist(bob); em.persist(alice); em.persist(james); 
    em.flush();    
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
}
