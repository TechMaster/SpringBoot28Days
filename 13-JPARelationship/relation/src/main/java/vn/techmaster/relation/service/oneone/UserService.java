package vn.techmaster.relation.service.oneone;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.relation.model.oneone.User;
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
    User u1 = new User("John", "john@gmail.com");
    UserDetail ud1 = new UserDetail("Developer", "1 Ngô Quyền, Hà nội");
    u1.setUserDetail(ud1);

    User u2 = new User("Anna", "anna@gmail.com");
    UserDetail ud2 = new UserDetail("Tester", "12 Nguyễn Du, Hà nội");
    u2.setUserDetail(ud2);

    em.persist(u1);
    em.persist(u2);
  }

  public List<User> getAll() {
    return userRepo.findAll();
  }
  
  public List<User> queryAll() {
    TypedQuery<User> query = em.createQuery("SELECT u FROM user u", User.class);
    return query.getResultList();
  }
}
