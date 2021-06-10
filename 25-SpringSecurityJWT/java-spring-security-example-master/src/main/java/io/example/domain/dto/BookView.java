package io.example.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookView {

    private String id;

    private UserView creator;
    private LocalDateTime createdAt;

    private String title;
    private String about;
    private String language;
    private List<String> genres;
    private String isbn13;
    private String isbn10;
    private String publisher;
    private LocalDate publishDate;
    private int hardcover;

}
