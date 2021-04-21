package vn.techmaster.bookstore;

import org.junit.jupiter.api.Test;

import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.extern.slf4j.Slf4j;
import vn.techmaster.bookstore.exception.APIException;
import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.BookPOJO;
import vn.techmaster.bookstore.restclient.APIErrorDecoder;
import vn.techmaster.bookstore.restclient.BookRequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;

@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
class FeignTest {
	private BookRequest bookClient;
	private Faker faker;
	@BeforeAll
	public void buildFeignClient() {
		final Decoder decoder = new GsonDecoder();
		final Encoder encoder = new GsonEncoder();
		bookClient = Feign.builder()
				// .retryer(new Retryer.Default(100, 300, 5))
				.client(new OkHttpClient()).decoder(decoder).encoder(encoder).logLevel(Logger.Level.BASIC)
				.errorDecoder(new APIErrorDecoder(decoder)).target(BookRequest.class, "http://localhost:8080/api/books");

		faker	= new Faker();
	}

	@Test
	@DisplayName(" 1. GET /api/books")
	@Order(1)
	void getAllBooks() {
		List<Book> books = bookClient.list();
		assertThat(books.size()).isGreaterThan(1);
	}

	@Test
	@DisplayName(" 2. GET /api/books/{id}")
	@Order(2)
	void getBookById() {
		Book book = bookClient.get(1L);
		assertThat(book).isNotNull();
	}

	@Test
	@DisplayName(" 3. POST /api/books")
	@Order(2)
	void postNewBook() {
		String title = faker.book().title();
		String author = faker.book().author();

		BookPOJO book = new BookPOJO(title, author);
		Book createdBook = bookClient.post(book);
		assertThat(createdBook.getId()).isPositive();
	}

	@Test
	@DisplayName(" 4. PUT /api/books/{id}")
	@Order(4)
	void updateNewBook() {
		String title = faker.book().title();
		String author = faker.book().author();

		BookPOJO book = new BookPOJO(title, author);
		Book updatedBook = bookClient.put(3, book);
		assertThat(updatedBook.getId()).isEqualTo(3);
		assertThat(updatedBook.getTitle()).isEqualTo(title);
		assertThat(updatedBook.getAuthor()).isEqualTo(author);
	}

	@Test
	@DisplayName(" 5. DELETE /api/books/{id}")
	@Order(5)
	void deleteBookById() {
		long idOfDeletedBook = bookClient.delete(4L);
		assertThat(idOfDeletedBook).isEqualTo(4L);
	}

	@Test
	@DisplayName(" 6. DELETE Not Found Book")
	@Order(6)
	void deleteNotFoundBook() {
		try {
			bookClient.delete(100L);
		} catch (APIException e) {
			assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(e.getMessage()).isEqualTo("BookStoreException : Cannot delete a book");
		}

	}

	@Test
	@DisplayName(" 7. Update Not Found Book")
	@Order(7)
	void UpdateNotFoundBook() {
		try {
			String title = faker.book().title();
			String author = faker.book().author();

			BookPOJO book = new BookPOJO(title, author);
			bookClient.put(100L, book);
		} catch (APIException e) {
			assertThat(e.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(e.getMessage()).isEqualTo("BookStoreException : Cannot update a book");
		}

	}

	@Test
	@DisplayName(" 8. Create 10 book")
	@Order(8)
	void post10NewBook() {
		List<Book> books = bookClient.list();
		long numberOfBooksBeforeAdd = books.size();
		for (int i = 0; i < 10; i++) {
			BookPOJO book = new BookPOJO(faker.book().title(), faker.book().author());
			bookClient.post(book);
		}
		List<Book> booksAfter = bookClient.list();
		assertThat(booksAfter.size() - numberOfBooksBeforeAdd).isEqualTo(10);
	}

	@Test
	@DisplayName(" 9. GET /api/books/slow")
	@Order(9)
	void getSlowBooks() {
		try {
			callSlowAPI();
		} catch (APIException ex) {
			assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.REQUEST_TIMEOUT);
			assertThat(ex.getMessage()).isEqualTo("BookStoreException : Timeout service");
		}
	}

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


	@Retryable(value = RuntimeException.class, maxAttempts = 6, backoff = @Backoff(delay = 1000))
	private void callSlowAPI() {
		bookClient.listSlow();
	}

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
}
