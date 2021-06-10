package io.example.domain.dto;

import lombok.Data;

@Data
public class SearchUsersQuery {

    private String id;
    private String username;
    private String fullName;

}
