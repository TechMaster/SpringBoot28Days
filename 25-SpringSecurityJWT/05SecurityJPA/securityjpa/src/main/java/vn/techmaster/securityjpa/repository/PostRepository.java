package vn.techmaster.securityjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.securityjpa.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  
}
