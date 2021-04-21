package vn.techmaster.bookstore.service;

import java.util.List;
import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;

public interface IBookService {
  List<Book> findAll();

  Book findById(Long id);

  Book save(BookPOJO book);

  Book update(long id, BookPOJO book);

  void updateTitle(long id, String title);

  void deleteById(long id);
}
