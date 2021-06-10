package io.example.api.data;

import io.example.domain.dto.BookView;
import io.example.domain.dto.EditBookRequest;
import io.example.service.BookService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
public class BookTestDataFactory {

    @Autowired
    private BookService bookService;

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about,
                               String language,
                               List<String> genres,
                               String isbn13,
                               String isbn10,
                               String publisher,
                               LocalDate publishDate,
                               Integer hardcover) {
        EditBookRequest createRequest = new EditBookRequest();
        createRequest.setAuthorIds(authorIds);
        createRequest.setTitle(title);
        createRequest.setAbout(about);
        createRequest.setLanguage(language);
        createRequest.setGenres(genres);
        createRequest.setIsbn13(isbn13);
        createRequest.setIsbn10(isbn10);
        createRequest.setPublisher(publisher);
        createRequest.setPublishDate(publishDate);
        createRequest.setHardcover(hardcover);

        BookView bookView = bookService.create(createRequest);

        assertNotNull(bookView.getId(), "Book id must not be null!");
        assertEquals(title, bookView.getTitle(), "Book title update isn't applied!");

        return bookView;
    }

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about,
                               String language,
                               List<String> genres,
                               String isbn13,
                               String isbn10,
                               String publisher,
                               LocalDate publishDate) {
        return createBook(authorIds, title, about, language, genres, isbn13, isbn10, publisher, publishDate, null);
    }

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about,
                               String language,
                               List<String> genres,
                               String isbn13,
                               String isbn10,
                               String publisher) {
        return createBook(authorIds, title, about, language, genres, isbn13, isbn10, publisher, null, null);
    }

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about,
                               String language,
                               List<String> genres,
                               String isbn13,
                               String isbn10) {
        return createBook(authorIds, title, about, language, genres, isbn13, isbn10, null, null, null);
    }

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about,
                               String language,
                               List<String> genres,
                               String isbn13) {
        return createBook(authorIds, title, about, language, genres, isbn13, null, null, null, null);
    }

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about,
                               String language,
                               List<String> genres) {
        return createBook(authorIds, title, about, language, genres, null, null, null, null, null);
    }

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about,
                               String language) {
        return createBook(authorIds, title, about, language, null, null, null, null, null, null);
    }

    public BookView createBook(List<String> authorIds,
                               String title,
                               String about) {
        return createBook(authorIds, title, about, null, null, null, null, null, null, null);
    }

    public BookView createBook(List<String> authorIds,
                               String title) {
        return createBook(authorIds, title, null, null, null, null, null, null, null, null);
    }

    public void deleteBook(String id) {
        bookService.delete(new ObjectId(id));
    }

}
