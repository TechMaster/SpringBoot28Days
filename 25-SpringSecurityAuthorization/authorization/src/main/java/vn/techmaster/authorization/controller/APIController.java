package vn.techmaster.authorization.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.authorization.model.Post;
import vn.techmaster.authorization.repository.PostRepository;

@RestController
@RequestMapping("/api")
public class APIController {
  @Autowired private PostRepository postRepo;

  @GetMapping("/user/{username}")
  @PreAuthorize("isAuthenticated() and #username == authentication.principal.username")
  public String getMyRoles(@PathVariable("username") String username) {
      SecurityContext securityContext = SecurityContextHolder.getContext();
      return securityContext.getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
  }

  @PreAuthorize("isAuthenticated()") //Phải đảm bảo là user login để có principal.id
  @PostAuthorize("returnObject.user.id == authentication.principal.id")
  @GetMapping("/post/{id}")
  public Post showEditPostForm(@PathVariable("id") long id) {
      Optional<Post> oPost = postRepo.findById(id);
      if (oPost.isPresent()) {
        return oPost.get();
      } else {
        throw new RuntimeException("Cannot find post with id " + id);
      }
  }

  @PreAuthorize("isAuthenticated()")
  @PostFilter("filterObject.user.id == authentication.principal.id")
  @GetMapping("/post")
  public List<Post> getPostsOfAnUser() {
    return postRepo.findAll();
  }
}
