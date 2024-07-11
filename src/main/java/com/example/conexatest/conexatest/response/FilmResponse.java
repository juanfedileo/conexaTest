package com.example.conexatest.conexatest.response;

import com.example.conexatest.conexatest.dto.FilmDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private List<FilmDto> results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FilmDto> getResults() {
        return results;
    }

    public void setResults(List<FilmDto> results) {
        this.results = results;
    }
}
