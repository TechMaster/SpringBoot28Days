package io.example.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EditAuthorRequest {

    @NotNull
    private String fullName;
    private String about;
    private String nationality;
    private List<String> genres;

}
