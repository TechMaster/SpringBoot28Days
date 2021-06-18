package vn.techmaster.vincinema.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Book;
import vn.techmaster.vincinema.model.User;

@RestController
@PreAuthorize("isAuthenticated()") //Chỉ cần đăng nhập là ok
@RequestMapping(value = "/api/book")
public class BookController {
  @GetMapping()
  public ResponseEntity<List<Book>> findAll() {
    List<Book> books = new ArrayList<>();
    books.add(new Book("Dế Mèn Phiêu Lưu Ký", "Tô Hoài"));
    books.add(new Book("Cọp Trắng", "Ramesh Asign"));
    return ResponseEntity.ok().body(books);
  }

  @GetMapping("/{username}")
  @PreAuthorize("#username == 'bob'")  //http://localhost:8080/api/book/bob là pass luôn nhưng http://localhost:8080/api/book/alice thì failed
  public ResponseEntity<String> checkBob(@PathVariable("username") String username, Authentication auth) { 
    System.out.println(auth.getName());
    return ResponseEntity.ok().body("Authorized ! " + username);
  }

  @GetMapping("/check/{username}")
  //https://docs.spring.io/spring-security/site/docs/current/reference/html5/#el-common-built-in
  @PreAuthorize("#username == principal.getUsername()")
  public ResponseEntity<String> checkUserName(@PathVariable("username") String username) {    
    return ResponseEntity.ok().body("Authorized ! " + username);
  }

  @GetMapping("/free") 
  @PreAuthorize("permitAll")  
  public ResponseEntity<String> freeAccess() {    
    return ResponseEntity.ok().body("No need login");
  }
  
}
