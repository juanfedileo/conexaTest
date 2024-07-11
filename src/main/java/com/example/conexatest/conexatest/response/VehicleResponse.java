package com.example.conexatest.conexatest.response;

import com.example.conexatest.conexatest.dto.VehicleDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleResponse {
    private List<VehicleDto> results;
    private int total_records;
    private int total_pages;
    public List<VehicleDto> getResults(){
        return results;
    }
    public void setResults(List<VehicleDto> results){
        this.results=results;
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
}
