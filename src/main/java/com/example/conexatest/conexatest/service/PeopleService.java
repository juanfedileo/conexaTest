package com.example.conexatest.conexatest.service;

import com.example.conexatest.conexatest.response.PeopleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class PeopleService {

    private final WebClient webClient;

    @Autowired
    public PeopleService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public PeopleResponse getPeople(int page, int size, String nameOrId){
        try {

            // Si nameOrId es un número, busca por ID
            if (nameOrId != null && nameOrId.matches("\\d+")) {
                return getPeopleById(nameOrId);
            }

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.swapi.tech/api/people");

            if (page != 0){
                builder.queryParam("page", page);
            }
            builder.queryParam("limit", size);
            if (nameOrId != null && !nameOrId.isEmpty()) {
                builder.queryParam("name", nameOrId);
            }

            String url = builder.toUriString();

            Mono<ResponseEntity<String>> responseMono = this.webClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(String.class);

            ResponseEntity<String> responseEntity = responseMono.block();

            if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();
                if (responseBody != null && responseBody.contains("results")) {
                    return StarWarsParseService.parseStandardResponse(responseBody);
                } else if (responseBody != null && responseBody.contains("result")) {
                    return StarWarsParseService.parseSearchResponse(responseBody);
                }
            }
            return new PeopleResponse();
        } catch (HttpClientErrorException e){
            System.err.println("Error al llamar a la API de Star Wars: " + e.getMessage());
            e.printStackTrace();
            return new PeopleResponse();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public PeopleResponse getPeopleById(String id) throws JsonProcessingException {
        try {
            String url = "https://www.swapi.tech/api/people/" + id;

            Mono<ResponseEntity<String>> responseMono = this.webClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(String.class);

            try {
                ResponseEntity<String> responseEntity = responseMono.block();

                if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
                    return StarWarsParseService.parseIdResponse(responseEntity.getBody());
                } else {
                    // Trato otro tipo de errores
                    System.err.println("Unexpected response or status code: " + responseEntity);
                    return new PeopleResponse();
                }
            } catch (WebClientResponseException.NotFound ex) {
                // Trato el error 404
                System.err.println("Person with ID " + id + " not found in SWAPI");
                PeopleResponse peopleResponse = new PeopleResponse();
                peopleResponse.setResults(new ArrayList<>());
                peopleResponse.setTotal_records(0);
                peopleResponse.setTotal_pages(1);
                return peopleResponse;
            }
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Client Error: " + e.getMessage());
            e.printStackTrace();
            return new PeopleResponse();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return new PeopleResponse();
        }
    }
}
