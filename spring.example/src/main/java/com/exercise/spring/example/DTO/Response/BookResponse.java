package com.exercise.spring.example.DTO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    @JsonProperty("ID")
    private int ID;
    private String name;
    @JsonProperty("ISBN")
    private String ISBN;
}
