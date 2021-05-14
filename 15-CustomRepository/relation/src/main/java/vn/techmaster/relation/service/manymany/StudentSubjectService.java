package vn.techmaster.relation.service.manymany;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.manymany.separate_primary_key.Student;
import vn.techmaster.relation.model.manymany.separate_primary_key.Subject;
import vn.techmaster.relation.repository.manymany.StudentRepository;
import vn.techmaster.relation.model.manymany.separate_primary_key.StudentSubject;

@Service
public class StudentSubjectService {
  @PersistenceContext
  private EntityManager em;

  @Autowired
  private StudentRepository studentRepo;

  
  @Transactional
  public void generateStudentSubject() {
    Student tom = new Student("Tom");
    Student bob = new Student("Bob");
    Student alice = new Student("Alice");
    Student jane = new Student("Jane");

    Subject math = new Subject("Math");
    Subject music = new Subject("Music");
    Subject computer = new Subject("Computer");

    StudentSubject tomMath = new StudentSubject(tom, math, 10f);
    StudentSubject tomMusic = new StudentSubject(tom, music, 9f);

    StudentSubject bobComputer = new StudentSubject(bob, computer, 7f);

    StudentSubject aliceMusic = new StudentSubject(alice, music, 8f);
    StudentSubject janeMath = new StudentSubject(jane, math, 9f);

    em.persist(tom);
    em.persist(bob);
    em.persist(alice);
    em.persist(jane);

    em.persist(math);
    em.persist(music);
    em.persist(computer);

    em.persist(tomMath); em.persist(tomMusic);
    em.persist(bobComputer);

    em.persist(aliceMusic);
    em.persist(janeMath);
    em.flush();
  }

  public List<Student> getAllStudents() {
    return studentRepo.findAll();
  }
  
}
