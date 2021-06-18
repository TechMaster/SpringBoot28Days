package vn.techmaster.authorization.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.techmaster.authorization.repository.PostRepository;

@Controller
public class HomeController {
  @Autowired private PostRepository postRepository;
  
  @GetMapping("/")
  public String showHomePage(Principal principal, Model model) {
    String loginName = (principal != null) ? principal.getName() : "";

    var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

    model.addAttribute("login_name", loginName);
    model.addAttribute("authorities", authorities);
    model.addAttribute("posts", postRepository.findAll());

    return "index";
  }

  @GetMapping("/free")
  public String showFree() {
    return "free";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String showAdmin() {
    return "admin";
  }

  @PreAuthorize("hasAuthority('USER')")
  @GetMapping("/user")
  public String showUserPage() {
    return "user";
  }

  @GetMapping("/author")
  @PreAuthorize("hasAuthority('AUTHOR')")
  public String showAuthorPage() {
    return "author";
  }

  @GetMapping("/editor")
  @PreAuthorize("hasAuthority('EDITOR')")
  public String showEditorPage() {
    return "editor";
  }

  @GetMapping("/editor2")
  //@RolesAllowed("ROLE_EDITOR")
  @PreAuthorize("hasRole('ROLE_EDITOR')")
  public String showEditorPage2() {
    return "editor";
  }

}
