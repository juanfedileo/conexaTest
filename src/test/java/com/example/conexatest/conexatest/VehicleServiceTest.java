package com.example.conexatest.conexatest;
import com.example.conexatest.conexatest.response.VehicleResponse;
import com.example.conexatest.conexatest.service.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = AppConfig.class)
public class VehicleServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private VehicleService vehicleService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl("https://www.swapi.tech/api")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        vehicleService = new VehicleService(webClientBuilder);
    }

    @Test
    public void testGetVehicles() throws JsonProcessingException {
        // Mock al WebClient
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        // Define la respuesta esperada como un JSON válido
        String jsonResponse = "{\"message\":\"ok\",\"total_records\":39,\"total_pages\":4,\"previous\":null,\"next\":\"https://www.swapi.tech/api/vehicles?page=2&limit=10\",\"results\":[{\"uid\":\"4\",\"name\":\"Sand Crawler\",\"url\":\"https://www.swapi.tech/api/vehicles/4\"},{\"uid\":\"7\",\"name\":\"X-34 landspeeder\",\"url\":\"https://www.swapi.tech/api/vehicles/7\"},{\"uid\":\"6\",\"name\":\"T-16 skyhopper\",\"url\":\"https://www.swapi.tech/api/vehicles/6\"},{\"uid\":\"8\",\"name\":\"TIE/LN starfighter\",\"url\":\"https://www.swapi.tech/api/vehicles/8\"},{\"uid\":\"14\",\"name\":\"Snowspeeder\",\"url\":\"https://www.swapi.tech/api/vehicles/14\"},{\"uid\":\"18\",\"name\":\"AT-AT\",\"url\":\"https://www.swapi.tech/api/vehicles/18\"},{\"uid\":\"16\",\"name\":\"TIE bomber\",\"url\":\"https://www.swapi.tech/api/vehicles/16\"},{\"uid\":\"19\",\"name\":\"AT-ST\",\"url\":\"https://www.swapi.tech/api/vehicles/19\"},{\"uid\":\"20\",\"name\":\"Storm IV Twin-Pod cloud car\",\"url\":\"https://www.swapi.tech/api/vehicles/20\"},{\"uid\":\"24\",\"name\":\"Sail barge\",\"url\":\"https://www.swapi.tech/api/vehicles/24\"}]}";
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>(jsonResponse, HttpStatus.OK)));

        // Llamada al metodo a probar
        VehicleResponse response = vehicleService.getVehicles(1, 10, null);

        // Asserts
        assertNotNull(response);
        assertEquals(10, response.getResults().size());
    }

    @Test
    public void testGetVehicleById() throws JsonProcessingException {
        // Mock the WebClient behavior
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>("{\"result\": {\"properties\": {\"name\": \"Sand Crawler\", \"url\": \"https://www.swapi.tech/api/vehicles/4\"}, \"uid\": \"4\"}}", HttpStatus.OK)));

        // Call the method to test
        VehicleResponse response = vehicleService.getVehicleById("4");

        // Assert the response
        assertNotNull(response);
        assertEquals("Sand Crawler", response.getResults().get(0).getName());
    }
}
