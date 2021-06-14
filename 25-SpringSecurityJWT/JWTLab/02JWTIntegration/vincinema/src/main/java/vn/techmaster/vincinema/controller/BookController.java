package vn.techmaster.vincinema.controller;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.model.Book;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/api/book")
public class BookController {
  @GetMapping()
  public ResponseEntity<List<Book>> findAll() {
    List<Book> books = new ArrayList<>();
    books.add(new Book("Dế Mèn Phiêu Lưu Ký", "Tô Hoài"));
    books.add(new Book("Cọp Trắng", "Ramesh Asign"));
    return ResponseEntity.ok().body(books);
  }  
}
