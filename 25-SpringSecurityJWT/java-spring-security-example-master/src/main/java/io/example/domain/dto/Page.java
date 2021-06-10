package io.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data @NoArgsConstructor @AllArgsConstructor
public class Page {

    @Min(value = 1, message = "Paging must start with page 1")
    private long number = 1;
    @Min(value = 1, message = "You can request minimum 1 records")
    @Max(value = 100, message = "You can request maximum 100 records")
    private long limit = 10;

}
