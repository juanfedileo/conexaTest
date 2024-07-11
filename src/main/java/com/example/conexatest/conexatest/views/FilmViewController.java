package com.example.conexatest.conexatest.views;

import com.example.conexatest.conexatest.response.FilmResponse;
import com.example.conexatest.conexatest.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmViewController {

    @Autowired
    private FilmService filmService;

    @GetMapping(value = "/films")
    public String getFilmsView(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(required = false) String name,
                               Model model) {
        FilmResponse filmResponse = filmService.getFilms(page, size, name);
        model.addAttribute("films", filmResponse.getResults());
        model.addAttribute("currentPage", page); // Añadir el número de página actual
        model.addAttribute("searchName", name);
        return "films";
    }
}
