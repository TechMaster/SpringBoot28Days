package vn.techmaster.bookstore.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;
import vn.techmaster.bookstore.exception.BookStoreException;
import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;
import vn.techmaster.bookstore.service.IBookService;

@RestController
@RequestMapping(value = "/api/")
@Slf4j
public class APIController {
  @Autowired
  private IBookService bookService;  

  @GetMapping(value = "/books")
  public ResponseEntity<List<Book>> findAllBooks() {
    List<Book> books = bookService.findAll();
    return ResponseEntity.ok(books);
  }

  @GetMapping("/books/{bookId}")
  public ResponseEntity<Book> findBookById(@PathVariable long bookId) {
    Book book = bookService.findById(bookId);
    return ResponseEntity.ok(book);
  }

  @PostMapping("/books")
  public ResponseEntity<Book> addBook(@RequestBody BookPOJO book) {
    Book newBook = bookService.save(book);
    try {
      return ResponseEntity.created(new URI("/api/books/" + newBook.getId())).body(newBook);
    } catch (URISyntaxException e) {
      log.error(e.getMessage());
      return ResponseEntity.ok().body(newBook);      
    }    
  }

  @PutMapping("/books/{bookId}")
  public ResponseEntity<Book> updateBook(@RequestBody BookPOJO book, @PathVariable long bookId) {   
    Book updatedBook = bookService.update(bookId, book);
    return ResponseEntity.ok().body(updatedBook);    
  }

  @PatchMapping("/books/{bookId}")
  public ResponseEntity<String> updateBookTitle(@RequestBody String title, @PathVariable long bookId) {   
    bookService.updateTitle(bookId, title);
    return ResponseEntity.ok().body(title);  //Nội dung đã thay đổi thành công  
  }

  @DeleteMapping(path = "/books/{bookId}")
  public ResponseEntity<Long> deleteBookById(@PathVariable long bookId) {
    bookService.deleteById(bookId);
    return ResponseEntity.ok(bookId);
  }

  @GetMapping(value = "/books/slow")
  public ResponseEntity<List<Book>> findSlowBooks(HttpServletRequest request){
    Random random = new Random();
    int ranValue = random.nextInt(6);

    if (ranValue > 4) {
      List<Book> books = bookService.findAll();
      return ResponseEntity.ok(books);
    }

    try {
      Thread.sleep(1000);
      log.debug("After sleep");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();  
      throw new BookStoreException("Sleep is interrupted", e, HttpStatus.REQUEST_TIMEOUT);
    }
    
    log.error("Timeout service");
    throw new BookStoreException("Timeout service", HttpStatus.REQUEST_TIMEOUT);
  }

}
