package com.example.conexatest.conexatest.service;

import com.example.conexatest.conexatest.dto.FilmDto;
import com.example.conexatest.conexatest.dto.PeopleDto;
import com.example.conexatest.conexatest.dto.StarshipDto;
import com.example.conexatest.conexatest.dto.VehicleDto;
import com.example.conexatest.conexatest.response.FilmResponse;
import com.example.conexatest.conexatest.response.PeopleResponse;
import com.example.conexatest.conexatest.response.StarshipResponse;
import com.example.conexatest.conexatest.response.VehicleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class StarWarsParseService {

    public static PeopleResponse parseStandardResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, PeopleResponse.class);
    }

    public static PeopleResponse parseSearchResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        PeopleResponse peopleResponse = new PeopleResponse();

        List<PeopleDto> results = new ArrayList<>();
        root.path("result").forEach(node -> {
            PeopleDto dto = new PeopleDto();
            dto.setUid(Integer.parseInt(node.path("uid").asText()));
            dto.setName(node.path("properties").path("name").asText());
            dto.setUrl(node.path("properties").path("url").asText());
            results.add(dto);
        });

        peopleResponse.setResults(results);
        peopleResponse.setTotal_records(results.size());
        peopleResponse.setTotal_pages(1);  // Assume 1 page since it's a filtered search

        return peopleResponse;
    }

    public static PeopleResponse parseIdResponse(String responseBody)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        PeopleResponse peopleResponse = new PeopleResponse();

        List<PeopleDto> results = new ArrayList<>();
        PeopleDto dto = new PeopleDto();
        dto.setUid(Integer.parseInt(root.path("result").path("uid").asText()));
        dto.setName(root.path("result").path("properties").path("name").asText());
        dto.setUrl(root.path("result").path("properties").path("url").asText());
        results.add(dto);

        peopleResponse.setResults(results);
        peopleResponse.setTotal_records(results.size());
        peopleResponse.setTotal_pages(1);

        return peopleResponse;
    }

    public static FilmResponse parseStandardFilmResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, FilmResponse.class);
    }

    public static FilmResponse parseSearchFilmResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        FilmResponse filmResponse = new FilmResponse();

        List<FilmDto> results = new ArrayList<>();
        root.path("result").forEach(node -> {
            FilmDto dto = new FilmDto();
            dto.setUid(Integer.parseInt(node.path("uid").asText()));
            dto.getProperties().setTitle(node.path("properties").path("title").asText());
            dto.getProperties().setUrl(node.path("properties").path("url").asText());
            results.add(dto);
        });

        filmResponse.setResults(results);

        return filmResponse;
    }

    public static FilmResponse parseIdFilmResponse(String responseBody)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        FilmResponse filmResponse = new FilmResponse();

        List<FilmDto> results = new ArrayList<>();
        FilmDto dto = new FilmDto();
        dto.setProperties(new FilmDto.FilmProperties());
        dto.setUid(Integer.parseInt(root.path("result").path("uid").asText()));
        dto.getProperties().setTitle(root.path("result").path("properties").path("title").asText());
        dto.getProperties().setUrl(root.path("result").path("properties").path("url").asText());
        results.add(dto);

        filmResponse.setResults(results);

        return filmResponse;
    }

    public static StarshipResponse parseStandardStarshipResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, StarshipResponse.class);
    }

    public static StarshipResponse parseSearchStarshipResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        StarshipResponse starshipResponse = new StarshipResponse();

        List<StarshipDto> results = new ArrayList<>();
        root.path("result").forEach(node -> {
            StarshipDto dto = new StarshipDto();
            dto.setUid(Integer.parseInt(node.path("uid").asText()));
            dto.setName(node.path("properties").path("name").asText());
            dto.setUrl(node.path("properties").path("url").asText());
            results.add(dto);
        });

        starshipResponse.setResults(results);
        starshipResponse.setTotal_records(results.size());
        starshipResponse.setTotal_pages(1);

        return starshipResponse;
    }

    public static StarshipResponse parseIdStarshipResponse(String responseBody)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        StarshipResponse starshipResponse = new StarshipResponse();

        List<StarshipDto> results = new ArrayList<>();
        StarshipDto dto = new StarshipDto();
        dto.setUid(Integer.parseInt(root.path("result").path("uid").asText()));
        dto.setName(root.path("result").path("properties").path("name").asText());
        dto.setUrl(root.path("result").path("properties").path("url").asText());
        results.add(dto);

        starshipResponse.setResults(results);

        return starshipResponse;
    }

    public static VehicleResponse parseStandardVehicleResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBody, VehicleResponse.class);
    }

    public static VehicleResponse parseSearchVehicleResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        VehicleResponse vehicleResponse = new VehicleResponse();

        List<VehicleDto> results = new ArrayList<>();
        root.path("result").forEach(node -> {
            VehicleDto dto = new VehicleDto();
            dto.setUid(Integer.parseInt(node.path("uid").asText()));
            dto.setName(node.path("properties").path("name").asText());
            dto.setUrl(node.path("properties").path("url").asText());
            results.add(dto);
        });

        vehicleResponse.setResults(results);

        return vehicleResponse;
    }

    public static VehicleResponse parseIdVehicleResponse(String responseBody)throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        VehicleResponse vehicleResponse = new VehicleResponse();

        List<VehicleDto> results = new ArrayList<>();
        VehicleDto dto = new VehicleDto();
        dto.setUid(Integer.parseInt(root.path("result").path("uid").asText()));
        dto.setName(root.path("result").path("properties").path("name").asText());
        dto.setUrl(root.path("result").path("properties").path("url").asText());
        results.add(dto);

        vehicleResponse.setResults(results);

        return vehicleResponse;
    }
}
