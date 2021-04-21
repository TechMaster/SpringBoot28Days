# Spring Retry

Spring Retry dùng để cố thử gọi lại một hàm vài lần không vượt quá Max Attemp. Các lần cố thử cách nhau một khoảng thời gian ngẫu nhiên hoặc bằng Backoff time.

Ví dụ khi kết nối một dịch vụ có tốc độ phản hồi lúc nhanh, lúc chậm, chúng ta có thể cố thử vài lần. Nếu quá số lần cho phép thì mới dừng và báo lỗi.


## 1. Thêm thử viện vào file [pom.xml](pom.xml)

```xml
<dependency>
  <groupId>org.springframework.retry</groupId>
  <artifactId>spring-retry</artifactId>
  <version>1.3.1</version>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aspects</artifactId>
  <version>5.3.6</version>
</dependency>
```

## 2. Tạo class cấu hình được annotate bởi ```@EnableRetry```
Xem [AppConfig.java](src/main/java/vn/techmaster/bookstore/config/AppConfig.java)
```java
@Configuration
@EnableRetry
public class AppConfig {
  
}
```

## 3. Sử dụng  ```RetryTemplate``` để thử vài lần
Trong file [FeignTest.java](src/test/java/vn/techmaster/bookstore/FeignTest.java)

```java
@Test
@DisplayName(" 10. Spring Retryable")
@Order(10)
void getSlowBooksSpringRetryable() {

  RetryTemplate template = RetryTemplate.builder().maxAttempts(4).fixedBackoff(1000).build();

  try {
    List<Book> books = template.execute(new RetryCallback<List<Book>, Exception>() {
      @Override
      public List<Book> doWithRetry(RetryContext context) {
        log.info("Retry attempt " + context.getRetryCount());
        try {
          return bookClient.listSlow();
        } catch (APIException apiEx) {
          throw new RuntimeException("Failed to execute after " + (context.getRetryCount() + 1));
        }
      }
    });

    assertThat(books.size()).isGreaterThan(1);
    log.info("Call slow API success with " + books.size() + " records returned");

  } catch (Exception ex) {			
    log.error(ex.getMessage());
  }

}
```

### 4. Giả lập một REST endpoint lúc nhanh, lúc chậm

```java
@GetMapping(value = "/books/slow")
public ResponseEntity<List<Book>> findSlowBooks(HttpServletRequest request){
  Random random = new Random();
  int ranValue = random.nextInt(6); //Giả lập ngẫu nhiên

  if (ranValue > 4) {  //Trả về thành công
    List<Book> books = bookService.findAll();
    return ResponseEntity.ok(books);
  }

  try {  //Cố tình dừng 1000 milliseconds
    Thread.sleep(1000);
    log.debug("After sleep");
  } catch (InterruptedException e) {
    Thread.currentThread().interrupt();  
    throw new BookStoreException("Sleep is interrupted", e, HttpStatus.REQUEST_TIMEOUT);
  }
  
  log.error("Timeout service");
  throw new BookStoreException("Timeout service", HttpStatus.REQUEST_TIMEOUT); //Sau đó ném ngoại lệ timeout
}
```

### 5. Log lại các nỗ lực thử gọi bằng Log4J2

Cấu hình ```File-Appender``` ghi ra ```${path}/unittest.log```
```xml
<File name="UnitTest-File-Appender" fileName="${path}/unittest.log">
  <PatternLayout>
    <pattern>
      [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n
    </pattern>
  </PatternLayout>
</File>
```

Chỉ lọc các log phát sinh từ ```class vn.techmaster.bookstore.FeignTest```
```xml
<Logger name="vn.techmaster.bookstore.FeignTest" level="debug" additivity="false">
  <AppenderRef ref="UnitTest-File-Appender" />
</Logger>
```

### 6. Dữ liệu xuất ra ở file [unittest.log](logs/unittest.log)
Cho thấy có những lần gọi API thành công sau vài lần thất bại. Có lần thì thất bại sau khi vượt quá số lần thử gọi cho phép (Max Attemp)
```
[INFO ] 2021-04-21 23:15:24.786 [main] FeignTest - Retry attempt 0
[INFO ] 2021-04-21 23:15:25.008 [main] FeignTest - Call slow API success with 6 records returned
[INFO ] 2021-04-21 23:15:30.842 [main] FeignTest - Retry attempt 0
[INFO ] 2021-04-21 23:15:33.907 [main] FeignTest - Retry attempt 1
[INFO ] 2021-04-21 23:15:36.930 [main] FeignTest - Retry attempt 2
[INFO ] 2021-04-21 23:15:39.954 [main] FeignTest - Retry attempt 3
[ERROR] 2021-04-21 23:15:41.974 [main] FeignTest - Failed to execute after 4
```