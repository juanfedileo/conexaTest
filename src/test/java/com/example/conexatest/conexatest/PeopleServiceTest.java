package com.example.conexatest.conexatest;

import com.example.conexatest.conexatest.response.PeopleResponse;
import com.example.conexatest.conexatest.service.PeopleService;
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
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AppConfig.class)
public class PeopleServiceTest {
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
    private PeopleService peopleService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl("https://www.swapi.tech/api")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        peopleService = new PeopleService(webClientBuilder);
    }

    @Test
    public void testGetPeople() throws JsonProcessingException {
        // Mock WebClient
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        String jsonResponse = "{\"message\":\"ok\",\"total_records\":82,\"total_pages\":9,\"previous\":null,\"next\":\"https://www.swapi.tech/api/people?page=2&limit=10\",\"results\":[{\"uid\":\"1\",\"name\":\"Luke Skywalker\",\"url\":\"https://www.swapi.tech/api/people/1\"},{\"uid\":\"2\",\"name\":\"C-3PO\",\"url\":\"https://www.swapi.tech/api/people/2\"},{\"uid\":\"3\",\"name\":\"R2-D2\",\"url\":\"https://www.swapi.tech/api/people/3\"},{\"uid\":\"4\",\"name\":\"Darth Vader\",\"url\":\"https://www.swapi.tech/api/people/4\"},{\"uid\":\"5\",\"name\":\"Leia Organa\",\"url\":\"https://www.swapi.tech/api/people/5\"},{\"uid\":\"6\",\"name\":\"Owen Lars\",\"url\":\"https://www.swapi.tech/api/people/6\"},{\"uid\":\"7\",\"name\":\"Beru Whitesun lars\",\"url\":\"https://www.swapi.tech/api/people/7\"},{\"uid\":\"8\",\"name\":\"R5-D4\",\"url\":\"https://www.swapi.tech/api/people/8\"},{\"uid\":\"9\",\"name\":\"Biggs Darklighter\",\"url\":\"https://www.swapi.tech/api/people/9\"},{\"uid\":\"10\",\"name\":\"Obi-Wan Kenobi\",\"url\":\"https://www.swapi.tech/api/people/10\"}]}";
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>("{\"results\": []}", HttpStatus.OK)));

        // Llamada al método a probar
        PeopleResponse response = peopleService.getPeople(1, 10, null);

        // Asserts
        assertNotNull(response);
        assertEquals(0, response.getResults().size());
    }

    @Test
    public void testGetPeopleById() throws JsonProcessingException {
        // Mock WebClient
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>("{\"result\": {\"properties\": {\"name\": \"Luke Skywalker\", \"url\": \"https://www.swapi.tech/api/people/1\"}, \"uid\": \"1\"}}", HttpStatus.OK)));

        // Llamada al método a probar
        PeopleResponse response = peopleService.getPeopleById("1");

        // Asserts
        assertNotNull(response);
        assertEquals("Luke Skywalker", response.getResults().get(0).getName());
    }
}
