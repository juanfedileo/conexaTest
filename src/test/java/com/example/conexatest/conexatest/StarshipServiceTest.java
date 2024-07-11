package com.example.conexatest.conexatest;

import com.example.conexatest.conexatest.response.StarshipResponse;
import com.example.conexatest.conexatest.service.StarshipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AppConfig.class)
public class StarshipServiceTest {

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
    private StarshipService starshipService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl("https://www.swapi.tech/api")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        starshipService = new StarshipService(webClientBuilder);
    }

    @Test
    public void testGetStarships() throws JsonProcessingException {
        // Mock al WebClient
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        // Define la respuesta esperada como un JSON válido
        String jsonResponse = "{\"message\":\"ok\",\"total_records\":36,\"total_pages\":4,\"previous\":null,\"next\":\"https://www.swapi.tech/api/starships?page=2&limit=10\",\"results\":[{\"uid\":\"2\",\"name\":\"CR90 corvette\",\"url\":\"https://www.swapi.tech/api/starships/2\"},{\"uid\":\"3\",\"name\":\"Star Destroyer\",\"url\":\"https://www.swapi.tech/api/starships/3\"},{\"uid\":\"5\",\"name\":\"Sentinel-class landing craft\",\"url\":\"https://www.swapi.tech/api/starships/5\"},{\"uid\":\"9\",\"name\":\"Death Star\",\"url\":\"https://www.swapi.tech/api/starships/9\"},{\"uid\":\"11\",\"name\":\"Y-wing\",\"url\":\"https://www.swapi.tech/api/starships/11\"},{\"uid\":\"10\",\"name\":\"Millennium Falcon\",\"url\":\"https://www.swapi.tech/api/starships/10\"},{\"uid\":\"13\",\"name\":\"TIE Advanced x1\",\"url\":\"https://www.swapi.tech/api/starships/13\"},{\"uid\":\"15\",\"name\":\"Executor\",\"url\":\"https://www.swapi.tech/api/starships/15\"},{\"uid\":\"12\",\"name\":\"X-wing\",\"url\":\"https://www.swapi.tech/api/starships/12\"},{\"uid\":\"17\",\"name\":\"Rebel transport\",\"url\":\"https://www.swapi.tech/api/starships/17\"}]}";
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>(jsonResponse, HttpStatus.OK)));

        // Llamada al metodo a probar
        StarshipResponse response = starshipService.getStarships(1, 10, null);

        System.out.println("Response from testGetStarships: " + response);

        // Asserts
        assertNotNull(response);
        assertEquals(10, response.getResults().size());
    }

    @Test
    public void testGetStarshipById() throws JsonProcessingException {
        // Mock the WebClient behavior
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>("{\"result\": {\"properties\": {\"name\": \"Millennium Falcon\", \"url\": \"https://www.swapi.tech/api/starships/10\"}, \"uid\": \"10\"}}", HttpStatus.OK)));

        // Call the method to test
        StarshipResponse response = starshipService.getStarshipById("10");

        // Assert the response
        assertNotNull(response);
        assertEquals("Millennium Falcon", response.getResults().get(0).getName());
    }
}
