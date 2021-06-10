package io.example.api.data;

import io.example.domain.dto.AuthorView;
import io.example.domain.dto.EditAuthorRequest;
import io.example.service.AuthorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
public class AuthorTestDataFactory {

    @Autowired
    private AuthorService authorService;

    public AuthorView createAuthor(String fullName,
                                   String about,
                                   String nationality,
                                   List<String> genres) {
        EditAuthorRequest createRequest = new EditAuthorRequest();
        createRequest.setFullName(fullName);
        createRequest.setAbout(about);
        createRequest.setNationality(nationality);
        createRequest.setGenres(genres);

        AuthorView authorView = authorService.create(createRequest);

        assertNotNull(authorView.getId(), "Author id must not be null!");
        assertEquals(fullName, authorView.getFullName(), "Author name update isn't applied!");

        return authorView;
    }

    public AuthorView createAuthor(String fullName,
                                   String about,
                                   String nationality) {
        return createAuthor(fullName, about, nationality, null);
    }

    public AuthorView createAuthor(String fullName,
                                   String about) {
        return createAuthor(fullName, about, null, null);
    }

    public AuthorView createAuthor(String fullName) {
        return createAuthor(fullName, null, null, null);
    }

    public void deleteAuthor(String id) {
        authorService.delete(new ObjectId(id));
    }

}
