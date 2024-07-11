package com.example.conexatest.conexatest.service;
import com.example.conexatest.conexatest.response.PeopleResponse;
import com.example.conexatest.conexatest.response.VehicleResponse;
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
public class VehicleService {

    private final WebClient webClient;

    @Autowired
    public VehicleService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


    public VehicleResponse getVehicles(int page, int size, String nameOrId){
        try {
            // Si nameOrId es un número, busca por ID
            if (nameOrId != null && nameOrId.matches("\\d+")) {
                return getVehicleById(nameOrId);
            }

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.swapi.tech/api/vehicles");

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
                    return StarWarsParseService.parseStandardVehicleResponse(responseBody);
                } else if (responseBody != null && responseBody.contains("result")) {
                    return StarWarsParseService.parseSearchVehicleResponse(responseBody);
                }
            }
            return new VehicleResponse();
        } catch (HttpClientErrorException e){
            System.err.println("Error al llamar a la API de Star Wars: " + e.getMessage());
            e.printStackTrace();
            return new VehicleResponse();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public VehicleResponse getVehicleById(String id) throws JsonProcessingException {
        try {
            String url = "https://www.swapi.tech/api/vehicles/" + id;

            Mono<ResponseEntity<String>> responseMono = this.webClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(String.class);

            try{
                ResponseEntity<String> responseEntity = responseMono.block();

                if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
                    return StarWarsParseService.parseIdVehicleResponse(responseEntity.getBody());
                }else {
                    System.err.println("Unexpected response or status code: " + responseEntity);
                    return new VehicleResponse();
                }
            }catch (WebClientResponseException.NotFound ex){
                System.err.println("Person with ID " + id + " not found in SWAPI");
                VehicleResponse vehicleResponse = new VehicleResponse();
                vehicleResponse.setResults(new ArrayList<>());
                vehicleResponse.setTotal_records(0);
                vehicleResponse.setTotal_pages(0);
                return vehicleResponse;
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error al llamar a la API de Star Wars: " + e.getMessage());
            e.printStackTrace();
            return new VehicleResponse();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return new VehicleResponse();
        }
    }
}
