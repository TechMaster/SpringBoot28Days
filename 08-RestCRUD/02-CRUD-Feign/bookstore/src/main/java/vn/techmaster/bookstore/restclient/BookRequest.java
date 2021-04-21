package vn.techmaster.bookstore.restclient;

import java.util.List;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;
public interface BookRequest extends BaseRequest<Book, BookPOJO>{
  
  @Headers("Content-Type: application/json")
  @RequestLine("PATCH /{id}/")
  @Body("{title}")
  String patch(@Param("id") long id, @Param("title") String title);


  @RequestLine("GET /slow")
  List<Book> listSlow();
}