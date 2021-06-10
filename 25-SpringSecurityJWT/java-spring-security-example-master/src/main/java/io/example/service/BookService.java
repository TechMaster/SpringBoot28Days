package io.example.service;

import io.example.domain.dto.BookView;
import io.example.domain.dto.EditBookRequest;
import io.example.domain.dto.Page;
import io.example.domain.dto.SearchBooksQuery;
import io.example.domain.mapper.BookEditMapper;
import io.example.domain.mapper.BookViewMapper;
import io.example.domain.model.Author;
import io.example.domain.model.Book;
import io.example.repository.AuthorRepo;
import io.example.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final BookEditMapper bookEditMapper;
    private final BookViewMapper bookViewMapper;

    @Transactional
    public BookView create(EditBookRequest request) {
        Book book = bookEditMapper.create(request);

        book = bookRepo.save(book);
        updateAuthors(book);

        return bookViewMapper.toBookView(book);
    }

    @Transactional
    public BookView update(ObjectId id, EditBookRequest request) {
        Book book = bookRepo.getById(id);
        bookEditMapper.update(request, book);

        book = bookRepo.save(book);
        if (!CollectionUtils.isEmpty(request.getAuthorIds())) {
            updateAuthors(book);
        }

        return bookViewMapper.toBookView(book);
    }

    private void updateAuthors(Book book) {
        List<Author> authors = authorRepo.findAllById(book.getAuthorIds());
        authors.forEach(author -> author.getBookIds().add(book.getId()));
        authorRepo.saveAll(authors);
    }

    @Transactional
    public BookView delete(ObjectId id) {
        Book book = bookRepo.getById(id);

        bookRepo.delete(book);

        return bookViewMapper.toBookView(book);
    }

    public BookView getBook(ObjectId id) {
        Book book = bookRepo.getById(id);
        return bookViewMapper.toBookView(book);
    }

    public List<BookView> getBooks(Iterable<ObjectId> ids) {
        List<Book> books = bookRepo.findAllById(ids);
        return bookViewMapper.toBookView(books);
    }

    public List<BookView> getAuthorBooks(ObjectId authorId) {
        Author author = authorRepo.getById(authorId);
        return bookViewMapper.toBookView(bookRepo.findAllById(author.getBookIds()));
    }

    public List<BookView> searchBooks(Page page, SearchBooksQuery query) {
        return bookViewMapper.toBookView(bookRepo.searchBooks(page, query));
    }

}
