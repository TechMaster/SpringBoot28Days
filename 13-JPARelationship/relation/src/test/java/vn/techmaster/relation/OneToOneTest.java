package vn.techmaster.relation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import vn.techmaster.relation.model.oneone.User;
import vn.techmaster.relation.model.oneone.UserDetail;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;


@DataJpaTest
public class OneToOneTest {
  @Autowired
  private TestEntityManager em;

  @Test
  @DisplayName("01. Add User and UserDetail")
  public void addUserAndUserDetail() {
    User user = new User("John Hopkin", "john@gmail.com");    
    UserDetail userDetail = new UserDetail("Developer", "Sandiego California, USA");
    user.setUserDetail(userDetail);

    em.persist(user);
    assertThat(user.getId()).isEqualTo(userDetail.getId());
    assertThat(userDetail.getUser().getFullname()).isEqualTo("John Hopkin");
  }

  @Test
  @DisplayName("02. Add many User and UserDetail")
  public void addManyUserAndUserDetail() {
    Faker faker = new Faker();
    for (int i = 0; i < 10; i++) {
      User user = new User(faker.name().fullName(), faker.internet().emailAddress());    
      UserDetail userDetail = new UserDetail(faker.job().title(), faker.address().fullAddress());
      user.setUserDetail(userDetail);
      em.persist(user);
      assertThat(user.getId()).isEqualTo(userDetail.getId());
    }
  }
}
