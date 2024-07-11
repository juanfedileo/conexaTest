package com.example.conexatest.conexatest.views;

import com.example.conexatest.conexatest.response.PeopleResponse;
import com.example.conexatest.conexatest.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeopleViewController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/people")
    public String getPeopleView(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String name,
                                Model model) {
        PeopleResponse people = peopleService.getPeople(page, size, name);
        model.addAttribute("people", people.getResults());
        model.addAttribute("currentPage", page); // Añadir el número de página actual
        model.addAttribute("totalRecords", people.getTotal_records()); // Añadir el número de página actual
        model.addAttribute("totalPages", people.getTotal_pages()); // Añadir el número de página actual
        model.addAttribute("searchName", name);
        return "people";
    }
}
