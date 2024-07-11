package com.example.conexatest.conexatest.controllers;

import com.example.conexatest.conexatest.models.Starship;
import com.example.conexatest.conexatest.models.Vehicle;
import com.example.conexatest.conexatest.response.FilmResponse;
import com.example.conexatest.conexatest.response.PeopleResponse;
import com.example.conexatest.conexatest.response.StarshipResponse;
import com.example.conexatest.conexatest.response.VehicleResponse;
import com.example.conexatest.conexatest.service.FilmService;
import com.example.conexatest.conexatest.service.PeopleService;
import com.example.conexatest.conexatest.service.StarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.conexatest.conexatest.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class StarWarsController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private StarshipService starshipService;

    @GetMapping("/people")
    public PeopleResponse getPeople(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String name) {
        return peopleService.getPeople(page, size, name);
    }

    @GetMapping("/film")
    public FilmResponse getFilm(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String name) {
        return filmService.getFilms(page, size, name);
    }

    @GetMapping("/starships")
    public StarshipResponse getStarship(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(required = false) String name) {
        return starshipService.getStarships(page, size, name);
    }

    @GetMapping("/vehicles")
    public VehicleResponse getVehicle(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(required = false) String name) {
        return vehicleService.getVehicles(page, size, name);
    }
}
