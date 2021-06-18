package vn.techmaster.demoauth.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demoauth.repository.UserRoleRepository;

@RestController
@RequestMapping("/")
public class APIController {
  @Autowired private UserRoleRepository userRoleRepository;

  @Secured("ROLE_VIEWER")
  @GetMapping("username")
  public String getUsername() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return securityContext.getAuthentication().getName();
  }

  @GetMapping("/user/{username}")
  @PreAuthorize("#username == authentication.principal.username")
  public String getMyRoles(@PathVariable("username") String username) {
      SecurityContext securityContext = SecurityContextHolder.getContext();
      return securityContext.getAuthentication().getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(","));
  }
}
