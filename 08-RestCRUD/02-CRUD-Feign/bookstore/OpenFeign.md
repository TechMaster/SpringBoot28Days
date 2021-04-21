# Open Feign - REST Client

Phần này tôi giải thích cách tạo Open Feign client để kết nối REST API.
## 1. Bổ xung thư viện vào [pom.xml](pom.xml)

Thư viện lõi, buộc phải có
```xml
<dependency>
  <groupId>io.github.openfeign</groupId>
  <artifactId>feign-core</artifactId>
  <version>11.1</version>
</dependency>
```

Thư viện để serialize và deserialize đối tượng Java ra JSON hoặc từ JSON ra đối tượng Java.
```xml
<dependency>
  <groupId>io.github.openfeign</groupId>
  <artifactId>feign-gson</artifactId>
  <version>11.1</version>
</dependency>
```

Thư viện Okhttp dùng làm tầng vận chuyển http
```xml
<dependency>
  <groupId>io.github.openfeign</groupId>
  <artifactId>feign-okhttp</artifactId>
  <version>11.1</version>
</dependency>
```
## 2. Tạo một [BaseRequest.java](src/main/java/vn/techmaster/bookstore/restclient/BaseRequest.java)
Đây là một generic interface nhận 2 tham số kiểu là ```<V, VPOJO>```
Kiểu ```V``` sẽ là kiểu Entity được lưu xuống CSDL.
Kiểu ```VPOJO``` là kiểu Plain Old Java Object, chỉ gồm những trường mà Web Client gửi lên, không chứa thuộc tính ```id```.

Sở dĩ tôi bày đặt tạo ra generic interface ```interface BaseRequest<V, VPOJO>``` vì tôi muốn trong tương lại có thể dùng lại cho các kiểu Entity tương tự mà không phải viết lặp lại quá nhiều.

```java
@Headers("Accept: application/json")
interface BaseRequest<V, VPOJO> {
  @RequestLine("GET /")
  List<V> list();

  @RequestLine("GET /{id}")
  V get(@Param("id") long key);

  @Headers("Content-Type: application/json")
  @RequestLine("POST /")
  V post(VPOJO value);

  @Headers("Content-Type: application/json")
  @RequestLine("PUT /{id}/")
  V put(@Param("id") long id, VPOJO value);


  @RequestLine("DELETE /{id}/")
  long delete(@Param("id") long id);
}
```

## 3. Tạo Interface [BookRequest.java](target/classes/vn/techmaster/bookstore/restclient/BookRequest.class)

```java
public interface BookRequest extends BaseRequest<Book, BookPOJO>{
  
  @Headers("Content-Type: application/json")
  @RequestLine("PATCH /{id}/")
  @Body("{title}")
  String patch(@Param("id") long id, @Param("title") String title);

  @RequestLine("GET /slow")
  List<Book> listSlow();
}
```

## 4. Tạo OpenFeign client trong ```class FeignTest``` 
Trong [FeignTest.java](src/test/java/vn/techmaster/bookstore/FeignTest.java), trong mục ```@BeforeAll``` tôi khai báo ```buildFeignClient()```

```java
class FeignTest {
	private BookRequest bookClient;

	@BeforeAll
	public void buildFeignClient() {
		final Decoder decoder = new GsonDecoder();
		final Encoder encoder = new GsonEncoder();
		bookClient = Feign.builder()
				.client(new OkHttpClient())
        .decoder(decoder)
        .encoder(encoder)
        .logLevel(Logger.Level.BASIC)
				.errorDecoder(new APIErrorDecoder(decoder))
        .target(BookRequest.class, "http://localhost:8080/api/books");
	}
```

## 5. APIErrorDecoder - decode lỗi trả về từ REST rồi ném ra ```APIException```
Khi gọi request lên API, sẽ có trường hợp lỗi trả về do đó ở phương thức ```buildFeignClient``` tôi đăng ký ```.errorDecoder(new APIErrorDecoder(decoder))```

Xem [APIErrorDecoder.java](src/main/java/vn/techmaster/bookstore/restclient/APIErrorDecoder.java). Mục tiêu là decode response body thành ```APIError apiError```. Rồi từ đó ném ra ```APIException```.
Phía REST API server có thể trả về response là một đối tượng Exception ví dụ như ```BookStoreException```. Tuy nhiên tôi lại sử dụng kiểu ```APIError``` vì muốn customize dữ liệu trả về, thêm, bớt trường thông tin theo ý thích.

```java
public class APIErrorDecoder implements ErrorDecoder {

  final Decoder decoder;
  final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

  public APIErrorDecoder(Decoder decoder) {
    this.decoder = decoder;
  }

  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      APIError apiError = (APIError) decoder.decode(response, APIError.class);
      return new APIException(HttpStatus.valueOf(response.status()), 
      apiError.message, apiError.details);
      
    } catch (final IOException fallbackToDefault) {
      return defaultDecoder.decode(methodKey, response);
    }
  }
}
```