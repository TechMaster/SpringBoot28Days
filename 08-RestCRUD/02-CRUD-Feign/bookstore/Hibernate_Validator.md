# Validate model

Thêm vào [pom.xml](pom.xml)
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
  <version>2.4.5</version>
</dependency>
```

Bổ xung annotation để validate từng trường trong model [BookPOJO.java](src/main/java/vn/techmaster/bookstore/model/BookPOJO.java)
```java
public class BookPOJO {
  @Size(min = 2, max = 200, message = "title's length must be between 2 and 200")
  public String title;

  @Size(min = 2, max = 100, message = "author's length must be between 2 and 100")
  public String author;
}
```

Viết hàm ```validateBook(BookPOJO book)``` trong [BookService.java](src/main/java/vn/techmaster/bookstore/service/BookService.java)
```java
private void validateBook(BookPOJO book) {
  Set<ConstraintViolation<BookPOJO>> constraintViolations = validator.validate(book);
  if (!constraintViolations.isEmpty()) {
    //Nếu có lỗi thì throw Exception
    throw new BookStoreException("New book violates constrains", 
    new Throwable(
      constraintViolations
      .stream().map(ConstraintViolation::getMessage)
      .collect(Collectors.joining(", "))), 
    HttpStatus.BAD_REQUEST);
  }
}
```

Từ bây giờ trước khi lưu Book cần gọi phương thức ```validateBook(book);```
```java
@Override
public Book save(BookPOJO book) {
  validateBook(book);
  Book newBook = new Book(book.getTitle(), book.getAuthor());
  return bookRepo.save(newBook);
}
```

Trong [FeignTest.java](src/test/java/vn/techmaster/bookstore/FeignTest.java) bổ xung một test case mới, cố tình tạo ra một đối tượng BookPOJO có 2 thuộc tính vi phạm

```java
@Test
@DisplayName("10. POST /api/books invalid book")
@Order(10)
void postInvalidBook() {
  try {
    BookPOJO book = new BookPOJO("a", "b");
    bookClient.post(book);
  } catch (APIException e) {
    assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(e.getMessage()).isEqualTo("BookStoreException : New book violates constrains");
  }
}
```

Exception báo lỗi sẽ có dạng:

```json
{
  "detailMessage": "BookStoreException : New book violates constrains",
  "details": "title's length must be between 2 and 200, author's length must be between 2 and 100"
}
```