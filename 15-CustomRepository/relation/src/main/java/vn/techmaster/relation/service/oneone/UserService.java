package vn.techmaster.relation.service.oneone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.oneone.Users;
import vn.techmaster.relation.model.oneone.UserDetail;
import vn.techmaster.relation.repository.oneone.UserRepository;

@Service
public class UserService {
  @PersistenceContext
  private EntityManager em;

  @Autowired
  private UserRepository userRepo;

  @Transactional
  public void generateUsers() {
    Users u1 = new Users("John", "john@gmail.com");
    UserDetail ud1 = new UserDetail("Developer", "1 Ngô Quyền, Hà nội");
    u1.setUserDetail(ud1);

    Users u2 = new Users("Anna", "anna@gmail.com");
    UserDetail ud2 = new UserDetail("Tester", "12 Nguyễn Du, Hà nội");
    u2.setUserDetail(ud2);

    em.persist(u1);
    em.persist(u2);
  }

  public List<Users> getAll() {
    return userRepo.findAll();
  }
  
  public List<Users> queryAll() {
    TypedQuery<Users> query = em.createQuery("SELECT u FROM users u", Users.class);
    return query.getResultList();
  }
}
