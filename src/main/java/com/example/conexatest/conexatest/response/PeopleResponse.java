package com.example.conexatest.conexatest.response;

import com.example.conexatest.conexatest.dto.PeopleDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleResponse {
    private List<PeopleDto> results;
    private int total_records;
    private int total_pages;
    private String previous;
    private String next;
    public List<PeopleDto> getResults() {
        return results;
    }

    public void setResults(List<PeopleDto> results) {
        this.results = results;
    }

    public int getTotal_records() {
        return total_records;
    }

    public void setTotal_records(int total_records) {
        this.total_records = total_records;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
