package com.example.conexatest.conexatest;

import com.example.conexatest.conexatest.response.FilmResponse;
import com.example.conexatest.conexatest.service.FilmService;
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
public class FilmServiceTest {

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
    private FilmService filmService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(webClientBuilder.baseUrl("https://www.swapi.tech/api")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        filmService = new FilmService(webClientBuilder);
    }

    @Test
    public void testGetFilmsById() throws JsonProcessingException {
        String id = "1";
        String filmResponse = "{\n" +
                "  \"result\": {\n" +
                "    \"properties\": {\n" +
                "      \"producer\": \"Gary Kurtz, Rick McCallum\",\n" +
                "      \"director\": \"George Lucas\",\n" +
                "      \"title\": \"A New Hope\"\n" +
                "    },\n" +
                "    \"uid\": \"1\"\n" +
                "  }\n" +
                "}\n";

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>(filmResponse, HttpStatus.OK)));

        FilmResponse response = filmService.getFilmById(id);

        assertNotNull(response);
        assertEquals("A New Hope", response.getResults().get(0).getProperties().getTitle());
    }

    @Test
    public void testGetFilms() throws JsonProcessingException {
        // Mock al WebClient
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        String filmResponse = "{\n" +
                "  \"message\": \"ok\",\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"properties\": {\n" +
                "        \"title\": \"A New Hope\",\n" +
                "        \"producer\": \"Gary Kurtz, Rick McCallum\",\n" +
                "        \"director\": \"George Lucas\",\n" +
                "        \"uid\": \"1\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"properties\": {\n" +
                "        \"title\": \"The Empire Strikes Back\",\n" +
                "        \"producer\": \"Gary Kurtz, Rick McCallum\",\n" +
                "        \"director\": \"Irvin Kershner\",\n" +
                "        \"uid\": \"2\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"properties\": {\n" +
                "        \"title\": \"Return of the Jedi\",\n" +
                "        \"producer\": \"Howard G. Kazanjian, George Lucas, Rick McCallum\",\n" +
                "        \"director\": \"Richard Marquand\",\n" +
                "        \"uid\": \"3\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"properties\": {\n" +
                "        \"title\": \"The Phantom Menace\",\n" +
                "        \"producer\": \"Rick McCallum\",\n" +
                "        \"director\": \"George Lucas\",\n" +
                "        \"uid\": \"4\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"properties\": {\n" +
                "        \"title\": \"Attack of the Clones\",\n" +
                "        \"producer\": \"Rick McCallum\",\n" +
                "        \"director\": \"George Lucas\",\n" +
                "        \"uid\": \"5\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"properties\": {\n" +
                "        \"title\": \"Revenge of the Sith\",\n" +
                "        \"producer\": \"Rick McCallum\",\n" +
                "        \"director\": \"George Lucas\",\n" +
                "        \"uid\": \"6\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(new ResponseEntity<>(filmResponse, HttpStatus.OK)));

        FilmResponse response = filmService.getFilms(1, 10, null);

        assertNotNull(response);
        assertEquals("A New Hope", response.getResults().get(0).getProperties().getTitle());
    }
}
