package vn.techmaster.relation.service.onemany.bidirection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.onemany.bidirection.Department;
import vn.techmaster.relation.model.onemany.bidirection.Professor;

@Service
public class ProfessorDepartmentService {
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void generateProfessorDepartment() {
    Department scienceDept = new Department("Science");
    Department artDept = new Department("Art");
    Department sportDept = new Department("Sport");


    Professor albertEnstein = new Professor("Albert Enstein");
    Professor vangoh = new Professor("Vincent Vangoh");
    Professor michaelPhelp = new Professor("Michael Phelp");

    scienceDept.add(albertEnstein);
    artDept.add(vangoh);
    sportDept.add(michaelPhelp);

    em.persist(scienceDept);
    em.persist(artDept);
    em.persist(sportDept);
  
    
    em.remove(sportDept);
    em.flush(); 
  }
}
