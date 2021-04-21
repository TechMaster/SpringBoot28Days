# Faker sinh dữ liệu giả
Trong [FeignTest.java](src/test/java/vn/techmaster/bookstore/FeignTest.java), chúng ta thấy một số phương thức sinh dữ liệu ngẫu nhiên sử dụng một thư viện Faker. Thư viện này giúp ta không phải tự viết các hàm random sinh các kiểu dữ liệu ngẫu nhiên và vẫn đảm bảo tính hợp lý nữa.

## 1. Bổ xung vào pom.xml

```xml
<dependency>
  <groupId>com.github.javafaker</groupId>
  <artifactId>javafaker</artifactId>
  <version>1.0.2</version>
</dependency>
```

## 2. Tạo đối tượng Faker

```java
@TestInstance(Lifecycle.PER_CLASS)
class FeignTest {
	private Faker faker;
	@BeforeAll
	public void buildFeignClient() {
		faker	= new Faker();
	}
}
```

## 3. Trong mỗi test case, gọi faker để sinh dữ liệu ngẫu nhiên
```java
void postNewBook() {
  String title = faker.book().title();
  String author = faker.book().author();

  BookPOJO book = new BookPOJO(title, author);
  Book createdBook = bookClient.post(book);
  assertThat(createdBook.getId()).isPositive();
}
```