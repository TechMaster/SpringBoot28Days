# Kiểm thử REST API

## Công cụ, thư viện cần có

1. JUnit 5 có nhiều annotation mới hay hơn JUnit 4 rất nhiều
```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter-api</artifactId>
  <version>5.8.0-M1</version>
  <scope>test</scope>
</dependency>
```
2. AssertJ viết lệnh assertThat rất trong sáng
```xml
<dependency>
  <groupId>org.assertj</groupId>
  <artifactId>assertj-core</artifactId>
  <version>3.19.0</version>
  <scope>test</scope>
</dependency>
```
3. spring-boot-starter-webflux để tạo WebClient
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

## Dùng WebClient để các request đến REST API
WebClient trong webflux có khả năng tạo non-blocking request đến server. Để chập chững làm quen, bạn nên đọc các bài này:
- [Spring WebClient Tutorial with Examples](https://hellokoding.com/spring-webclient-tutorial-with-examples/)
- [Spring WebFlux Tutorial](https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/)

Trước tiên hãy xem file [BarcelonateamApplicationTests.java](src/test/java/com/onemount/barcelonateam/BarcelonateamApplicationTests.java)

```java
class BarcelonateamApplicationTests {
	private WebClient webClient;

	@BeforeAll //Trước tất cả hàm kiểm thử thì tạo ra một đối tượng ```WebClient webClient```
	void buildWebClient() {
		webClient = WebClient
			.builder()
			.filter(ExchangeFilterFunction.ofResponseProcessor(this::renderApiErrorResponse))
			.baseUrl("http://localhost:8080")  //đường dẫn gốc đến REST API
			.build();		
	}
}
```

Đoạn thú vị nhất đó là ```.filter(ExchangeFilterFunction.ofResponseProcessor(this::renderApiErrorResponse))``` dùng để bắt các lỗi REST API trả về!

Khi có lỗi thì gọi vào ```Mono<ClientResponse> renderApiErrorResponse(ClientResponse clientResponse)```
```java
private Mono<ClientResponse> renderApiErrorResponse(ClientResponse clientResponse) {
  if(clientResponse.statusCode().isError()){
    return clientResponse
    .bodyToMono(APIError.class)
    .flatMap(apiErrorResponse -> Mono.error(new APIException(clientResponse.statusCode(), 
    apiErrorResponse.message, 
    apiErrorResponse.details)));
  }
  return Mono.just(clientResponse);
}
```

## 1. Team must have 11 players

Chúng làm quen với hàm kiểm thử đầu tiên: ```GET /team``` cần trả về đúng 11 cầu thủ.

```java
@Test
@DisplayName(" 1. Team must have 11 players")
void teamMustHave11Players() {
  Mono<Player[]> result = webClient.get()
      .uri(uriBuilder -> uriBuilder  //tạo đường dẫn
          .path("/team")
          .build())
      .accept(MediaType.APPLICATION_JSON)  //chọn kiểu dữ liệu trả về là JSON
      .retrieve()
      .bodyToMono(Player[].class) // chuyển response.body sang mảng các Player
      .log();

  Player[] players = result.block();
  assertThat(players).hasSize(11);
}
```
