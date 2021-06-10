package io.example.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.example.api.data.AuthorTestDataFactory;
import io.example.api.data.BookTestDataFactory;
import io.example.domain.dto.AuthorView;
import io.example.domain.dto.BookView;
import io.example.domain.dto.ListResponse;
import io.example.domain.dto.SearchBooksQuery;
import io.example.domain.dto.SearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static io.example.util.JsonHelper.fromJson;
import static io.example.util.JsonHelper.toJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestBookSearchApi {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AuthorTestDataFactory authorTestDataFactory;
    private final BookTestDataFactory bookTestDataFactory;

    @Autowired
    public TestBookSearchApi(MockMvc mockMvc,
                             ObjectMapper objectMapper,
                             AuthorTestDataFactory authorTestDataFactory,
                             BookTestDataFactory bookTestDataFactory) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.authorTestDataFactory = authorTestDataFactory;
        this.bookTestDataFactory = bookTestDataFactory;
    }

    @Test
    public void testSearch() throws Exception {
        AuthorView author1 = authorTestDataFactory.createAuthor("Book Search A Author");
        AuthorView author2 = authorTestDataFactory.createAuthor("Book Search B Author");
        AuthorView author3 = authorTestDataFactory.createAuthor("Book Search C Author");

        List<String> authorIds1 = List.of(author1.getId(), author2.getId());
        List<String> authorIds2 = List.of(author3.getId());

        BookView book1 = bookTestDataFactory.createBook(authorIds1, "Book Search A Book", null, null, List.of("Book Search Genre A", "Book Search Genre B"));
        BookView book2 = bookTestDataFactory.createBook(authorIds1, "Book Search B Book", null, null, null, "978-1-56619-909-4");
        BookView book3 = bookTestDataFactory.createBook(authorIds1, "Book Search C Book", null, null, null, null, "1-56619-909-3");
        BookView book4 = bookTestDataFactory.createBook(authorIds1, "Book Search D Book", null, null, null, null, null, "Book Search A Publisher");
        BookView book5 = bookTestDataFactory.createBook(authorIds1, "Book Search E Book", null, null, null, null, null, null, LocalDate.of(1985, 7, 17));
        BookView book6 = bookTestDataFactory.createBook(authorIds2, "Book Search F Book");
        BookView book7 = bookTestDataFactory.createBook(authorIds2, "Book Search G Book");
        BookView book8 = bookTestDataFactory.createBook(authorIds2, "Book Search H Book");
        BookView book9 = bookTestDataFactory.createBook(authorIds2, "Book Search I Book");
        BookView book10 = bookTestDataFactory.createBook(authorIds2, "Book Search J Book");

        testIdFilter(book1.getId());
        testTitleFilter();
        testGenresFilter();
        testIsbn13Filter();
        testIsbn10Filter();
        testPublisherFilter();
        testPublishDateFilter();
        testAuthorIdFilter(author1.getId());
        testAuthorFullNameFilter();

        bookTestDataFactory.deleteBook(book1.getId());
        bookTestDataFactory.deleteBook(book2.getId());
        bookTestDataFactory.deleteBook(book3.getId());
        bookTestDataFactory.deleteBook(book4.getId());
        bookTestDataFactory.deleteBook(book5.getId());
        bookTestDataFactory.deleteBook(book6.getId());
        bookTestDataFactory.deleteBook(book7.getId());
        bookTestDataFactory.deleteBook(book8.getId());
        bookTestDataFactory.deleteBook(book9.getId());
        bookTestDataFactory.deleteBook(book10.getId());

        authorTestDataFactory.deleteAuthor(author1.getId());
        authorTestDataFactory.deleteAuthor(author2.getId());
        authorTestDataFactory.deleteAuthor(author3.getId());
    }

    private void testIdFilter(String id) throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query with book id equal
        query = new SearchBooksQuery();
        query.setId(id);
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testTitleFilter() throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query book title contains
        query = new SearchBooksQuery();
        query.setTitle("Book Search G");
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");

        // Search query book title contains case insensitive
        query = new SearchBooksQuery();
        query.setTitle("Book Search g");
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testGenresFilter() throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query genres all
        query = new SearchBooksQuery();
        query.setGenres(Set.of("Book Search Genre A", "Book Search Genre B"));
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");

        // Search query genres mismatch
        query = new SearchBooksQuery();
        query.setGenres(Set.of("Book Search Genre A", "Book Search Genre C"));
        bookViewList = execute("/api/book/search", query);
        assertEquals(0, bookViewList.getItems().size(), "Invalid search result!");

        // Search query genres partial
        query = new SearchBooksQuery();
        query.setGenres(Set.of("Book Search Genre A"));
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testIsbn13Filter() throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query isbn13 equals
        query = new SearchBooksQuery();
        query.setIsbn13("978-1-56619-909-4");
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testIsbn10Filter() throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query isbn10 equals
        query = new SearchBooksQuery();
        query.setIsbn10("1-56619-909-3");
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testPublisherFilter() throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query book publisher contains
        query = new SearchBooksQuery();
        query.setPublisher("Book Search A Pub");
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");

        // Search query book publisher contains case insensitive
        query = new SearchBooksQuery();
        query.setPublisher("Book Search a");
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testPublishDateFilter() throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query publish date interval contains
        query = new SearchBooksQuery();
        query.setPublishDateStart(LocalDate.of(1985, 5, 1));
        query.setPublishDateEnd(LocalDate.of(1985, 9, 1));
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");

        // Search query publish date interval not contains
        query = new SearchBooksQuery();
        query.setPublishDateStart(LocalDate.of(1985, 8, 1));
        query.setPublishDateEnd(LocalDate.of(1985, 9, 1));
        bookViewList = execute("/api/book/search", query);
        assertEquals(0, bookViewList.getItems().size(), "Invalid search result!");

        // Search query publish date interval start is inclusive
        query = new SearchBooksQuery();
        query.setPublishDateStart(LocalDate.of(1985, 7, 17));
        query.setPublishDateEnd(LocalDate.of(1985, 9, 1));
        bookViewList = execute("/api/book/search", query);
        assertEquals(1, bookViewList.getItems().size(), "Invalid search result!");

        // Search query publish date interval end is exclusive
        query = new SearchBooksQuery();
        query.setPublishDateStart(LocalDate.of(1985, 5, 1));
        query.setPublishDateEnd(LocalDate.of(1985, 7, 17));
        bookViewList = execute("/api/book/search", query);
        assertEquals(0, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testAuthorIdFilter(String authorId) throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query with book id equal
        query = new SearchBooksQuery();
        query.setAuthorId(authorId);
        bookViewList = execute("/api/book/search", query);
        assertEquals(5, bookViewList.getItems().size(), "Invalid search result!");
    }

    private void testAuthorFullNameFilter() throws Exception {
        SearchBooksQuery query;
        ListResponse<BookView> bookViewList;

        // Search query author full name contains
        query = new SearchBooksQuery();
        query.setAuthorFullName("Book Search A");
        bookViewList = execute("/api/book/search", query);
        assertEquals(5, bookViewList.getItems().size(), "Invalid search result!");

        // Search query author full name contains case insensitive
        query = new SearchBooksQuery();
        query.setAuthorFullName("Book Search c");
        bookViewList = execute("/api/book/search", query);
        assertEquals(5, bookViewList.getItems().size(), "Invalid search result!");
    }

    private ListResponse<BookView> execute(String url, SearchBooksQuery query) throws Exception {
        MvcResult result = this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, new SearchRequest<>(query))))
                .andExpect(status().isOk())
                .andReturn();

        return fromJson(objectMapper,
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });
    }

}
