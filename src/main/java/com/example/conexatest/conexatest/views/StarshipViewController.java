package com.example.conexatest.conexatest.views;

import com.example.conexatest.conexatest.response.StarshipResponse;
import com.example.conexatest.conexatest.service.StarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class StarshipViewController {
    @Autowired
    private StarshipService starshipService;

    @GetMapping("/starships")
    public String getStarshipsView(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String name,
                                Model model) {

        StarshipResponse starships = starshipService.getStarships(page, size, name);
        model.addAttribute("starships", starships.getResults());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalRecords", starships.getTotal_records()); // Añadir el número de página actual
        model.addAttribute("totalPages", starships.getTotal_pages()); // Añadir el número de página actual
        model.addAttribute("searchName", name);// Añadir el número de página actual
        return "starships";
    }
}
