package vn.techmaster.bookstore.exception;

import org.springframework.http.HttpStatus;

public class BookStoreException extends RuntimeException {
  public HttpStatus status;
  public BookStoreException(String message, Throwable cause, HttpStatus status) {
    super(message, cause, false, false);
    this.status = status;
  }
  public BookStoreException(String message, HttpStatus status) {
    super(message, null, false, false);
    this.status = status;
  }

  public BookStoreException(String message) {
    super(message, null, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }

  public BookStoreException(String message, Throwable cause) {
    super(message, cause, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }
}
