package vn.techmaster.relation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import vn.techmaster.relation.model.onemany.bidirection.Comment;
import vn.techmaster.relation.model.onemany.bidirection.Post;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;

@DataJpaTest
public class OneToManyUnidirectionTest {
  @Autowired
  private TestEntityManager em;

  @Test
  @DisplayName("01. Add Post and Comment")
  public void addPostAndComment() {
    Faker faker = new Faker();
    Post post = new Post(faker.book().title(), faker.lorem().paragraph());
    for (int i = 0; i<3; i++) {
      Comment comment = new Comment(faker.harryPotter().quote());
      post.addComment(comment);
    }
    em.persist(post);
  
    
  }
}
