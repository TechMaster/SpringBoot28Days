package vn.techmaster.bookstore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vn.techmaster.bookstore.exception.BookStoreException;
import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;
import vn.techmaster.bookstore.repository.BookRespository;

@Service
public class BookService implements IBookService {
  @Autowired
  private BookRespository bookRepo;

  static final String BOOK_ID_NOT_EXIST = "Book id %d doest not exist.";
  private Validator validator;

  public BookService() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Override
  public List<Book> findAll() {
    return bookRepo.findAll();
  }

  @Override
  public Book findById(Long id) {    
    Optional<Book> optionalBook = bookRepo.findById(id);
    if (optionalBook.isPresent()) {
      return optionalBook.get();
    } else {
      throw new BookStoreException("Cannot find a book", 
      new Throwable(String.format(BOOK_ID_NOT_EXIST, id)), 
      HttpStatus.NOT_FOUND);
    }
  }

  private void validateBook(BookPOJO book) {
    Set<ConstraintViolation<BookPOJO>> constraintViolations = validator.validate(book);
    if (!constraintViolations.isEmpty()) {
      throw new BookStoreException("New book violates constrains", 
      new Throwable(
        constraintViolations
        .stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(", "))), 
      HttpStatus.BAD_REQUEST);
    }
  }
  @Override
  public Book save(BookPOJO book) {
    validateBook(book);
    Book newBook = new Book(book.getTitle(), book.getAuthor());
    return bookRepo.save(newBook);
  }

  @Override
  public Book update(long id, BookPOJO book) {
    validateBook(book);
    Book updatedBook = new Book(id, book.getTitle(), book.getAuthor());
    Optional<Book> optionalBook = bookRepo.findById(id);
    if (optionalBook.isPresent()) {
      bookRepo.save(updatedBook);
      return updatedBook;
    } else {
      throw new BookStoreException("Cannot update a book", 
      new Throwable(String.format(BOOK_ID_NOT_EXIST, id)), 
      HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void updateTitle(long id, String title) {
    Optional<Book> optionalBook = bookRepo.findById(id);
    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      book.setTitle(title);
      bookRepo.save(book);
    } else {
      throw new BookStoreException("Cannot update title of a book", 
      new Throwable(String.format(BOOK_ID_NOT_EXIST, id)), 
      HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void deleteById(long id) {
    Optional<Book> optionalBook = bookRepo.findById(id);
    if (optionalBook.isPresent()) {      
      bookRepo.delete(optionalBook.get());
    } else {
      throw new BookStoreException("Cannot delete a book", 
      new Throwable(String.format(BOOK_ID_NOT_EXIST, id)), 
      HttpStatus.NOT_FOUND);
    }
  }  
  
}
