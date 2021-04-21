Ví dụ lập trình này để minh hoạ cho bài viết [HTTP Methods in Spring RESTful Services](https://www.dariawan.com/tutorials/rest/http-methods-spring-restful-services/). Hãy đọc kỹ bài này. Viết tiếng Anh rất trong sáng rồi, dịch lại sợ không kỹ bằng tác giả. Tôi chỉ lập trình ví dụ để các bạn chạy thử nghiệm luôn.

## 1. Cấu trúc dự án
```
.
├── main
│   ├── java
│   │   └── vn
│   │       └── techmaster
│   │           └── bookstore
│   │               ├── controller
│   │               │   └── APIController.java  <-- Tạo REST API
│   │               ├── model
│   │               │   ├── Book.java <-- Định nghĩa Entity ánh xạ vào bảng trong CSDL
│   │               │   └── BookPOJO.java <-- Class đơn giản chứa vừa đủ các trường client gửi lên
│   │               ├── repository
│   │               │   └── BookRespository.java <-- Interface kế thừa JPA
│   │               ├── service
│   │               │   ├── BookService.java <-- Dịch vụ Book
│   │               │   └── IBookService.java
│   │               └── BookstoreApplication.java
│   └── resources
│       ├── application.properties
│       └── book.sql <-- File sql nạp một số bản ghi ban đầu
```

## 2. Cấu hình kết nối CSDL in memory SQL database H2
Bổ xung những dòng này vào file [application.properties](src/main/resources/application.properties)
```
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=123
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.properties.hibernate.hbm2ddl.import_files=book.sql
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
```
## 3. Định nghĩa Entity
[Book.java](src/main/java/vn/techmaster/bookstore/model/Book.java)
```java
@Entity(name = "book")
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = true)
  private String author;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }
}
```
## 4.  Nạp dữ liệu vào H2 khi app khởi động
Tạo file [book.sql](src/main/resources/book.sql) trong thư mục resource.
```sql
INSERT INTO book(title, author) VALUES('Ulysses', 'James Joyce');
INSERT INTO book(title, author) VALUES('The Great Gatsby', 'F. Scott Fitzgerald');
INSERT INTO book(title, author) VALUES('Brave New World', 'Aldous Huxley');
INSERT INTO book(title, author) VALUES('1984', 'George Orwell');
INSERT INTO book(title, author) VALUES('Tobacco Road', 'Erskine Caldwell');
INSERT INTO book(title, author) VALUES('Midnight’s Children', 'Salman Rushdie');
```

Dòng dưới trong [application.properties](src/main/resources/application.properties) thực hiện nạp dữ liệu từ book.sql vào H2
```
spring.jpa.properties.hibernate.hbm2ddl.import_files=book.sql
```


## 5. Thiết kế class BookPOJO
[BookPOJO.java](src/main/java/vn/techmaster/bookstore/model/BookPOJO.java)
Ở mỗi trường, có constrain annotation ```@Size```, xem [Hibernate_Validator.md](Hibernate_Validator.md) để biết thêm cơ chế kiểm tra dữ liệu. Dễ dàng thấy BookPOJO không có thuộc tính id
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPOJO {
  @Size(min = 2, max = 200, message = "title's length must be between 2 and 200")
  public String title;

  @Size(min = 2, max = 100, message = "author's length must be between 2 and 100")
  public String author;
}
```
## 6. Tạo [APIController.java](src/main/java/vn/techmaster/bookstore/controller/APIController.java)
```java
@RestController  //Đánh dấu đây là REST API

@RequestMapping(value = "/api/") // Đường dẫn gốc cho controller này là /api/
public class APIController {
  @Autowired
  private IBookService bookService;  // Sẽ gọi đến BookService

}
```

### 6.1 GET danh sách toàn bộ Book
Lấy danh sách các books. Lệnh này gọi nhiều lần, nhưng sẽ không thay đổi dữ liệu vì nó chỉ đọc. Gọi là Idempotent methods.
[APIController.java](src/main/java/vn/techmaster/bookstore/controller/APIController.java)
```java
@GetMapping(value = "/books")
public ResponseEntity<List<Book>> findAllBooks() {
  List<Book> books = bookService.findAll();
  return ResponseEntity.ok(books);
}
```

[BookService.java](src/main/java/vn/techmaster/bookstore/service/BookService.java)
```java
@Override
public List<Book> findAll() {
  return bookRepo.findAll();
}
```

```sh
$ curl -X GET "http://localhost:8080/api/books"

[{"id":1,"title":"Ulysses","author":"James Joyce"},{"id":2,"title":"The Great Gatsby","author":"F. Scott Fitzgerald"},{"id":3,"title":"Brave New World","author":"Aldous Huxley"},{"id":4,"title":"1984","author":"George Orwell"},{"id":5,"title":"Tobacco Road","author":"Erskine Caldwell"},{"id":6,"title":"Midnight’s Children","author":"Salman Rushdie"}]
```
Sử dụng công cụ PostMan, lựa chọn GET
![](images/GET.jpg)

### 6.2 GET by id
Tìm sách theo id cụ thể nếu thành công thì trả về mã 200, còn không tìm thấy trả về lỗi 404

[APIController.java](src/main/java/vn/techmaster/bookstore/controller/APIController.java)
```java
@GetMapping("/books/{bookId}")
public ResponseEntity<Book> findBookById(@PathVariable long bookId) {
  Optional<Book> optionalBook = bookService.findById(bookId);
  if (optionalBook.isPresent()) {
    return ResponseEntity.ok(optionalBook.get()); // return 200, with json body
  } else {
    throw new BookStoreException("Book is not found", HttpStatus.NOT_FOUND);
  }
}
```
[BookService.java](src/main/java/vn/techmaster/bookstore/service/BookService.java)
```java
@Override
public Optional<Book> findById(Long id) {    
  return bookRepo.findById(id);
}
```
![](images/GET_by_Id.jpg)

### 6.3 POST
Tạo mới bản ghi book. Mỗi lần gọi lại tạo ra bản ghi mới, dữ liệu trên server không còn đồng nhất. Đây là inidempotent method
```java
@PostMapping("/books")
public ResponseEntity<Book> addBook(@RequestBody BookPOJO book) {
  Book newBook = bookService.save(book);
  try {
    return ResponseEntity.created(new URI("/api/books/" + newBook.getId())).body(newBook);
  } catch (URISyntaxException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  } 
}
```

```java
@Override
public Book save(BookPOJO book) {
  Book newBook = new Book(book.getTitle(), book.getAuthor());
  return bookRepo.save(newBook);
}
```
![](images/POST.jpg)

### 6.4 PUT cập nhật hầu hết hoặc bất kỳ trường nào

Để sửa đổi toàn bộ, thay thế bản ghi. Hoặc không rõ sửa trường nào, thì gửi đối tượng chứa đủ các trường dữ liệu lên server

```java
@PutMapping("/books/{bookId}")
public ResponseEntity<Book> updateBook(@RequestBody BookPOJO book, @PathVariable long bookId) {   
  Book updatedBook = bookService.update(bookId, book);
  return ResponseEntity.ok().body(updatedBook);    
}
```

Phía [BookService.java](src/main/java/vn/techmaster/bookstore/service/BookService.java)
```java
@Override
public Book update(long id, BookPOJO book) {
  validateBook(book);  
  Optional<Book> optionalBook = bookRepo.findById(id);
  if (optionalBook.isPresent()) {
    Book updatedBook = new Book(id, book.getTitle(), book.getAuthor());
    bookRepo.save(updatedBook);
    return updatedBook;
  } else {
    throw new BookStoreException("Cannot update a book", 
    new Throwable(String.format(BOOK_ID_NOT_EXIST, id)), 
    HttpStatus.NOT_FOUND);
  }
}
```

![](images/PUT.jpg)

## 6.5 PATCH chỉ sửa một hoặc vài trường cụ thể

```java
@PatchMapping("/books/{bookId}")
public ResponseEntity<String> updateBookTitle(@RequestBody String title, @PathVariable long bookId) {   
  bookService.updateTitle(bookId, title);
  return ResponseEntity.ok().body(title);  //Nội dung đã thay đổi thành công  
}
```

```java
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
```

![](images/PATCH.jpg)

## 6.6 DELETE

```java
@DeleteMapping(path = "/books/{bookId}")
public ResponseEntity<Long> deleteBookById(@PathVariable long bookId) {
  bookService.deleteById(bookId);
  return ResponseEntity.ok(bookId);
}
```

```java
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
```

![](images/DELETE.jpg)