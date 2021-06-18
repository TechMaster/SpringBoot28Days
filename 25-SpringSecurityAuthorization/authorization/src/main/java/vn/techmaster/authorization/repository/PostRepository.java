package vn.techmaster.authorization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.authorization.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByUserUsername(String username);
}
