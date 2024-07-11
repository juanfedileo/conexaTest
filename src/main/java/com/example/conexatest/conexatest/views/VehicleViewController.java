package com.example.conexatest.conexatest.views;

import com.example.conexatest.conexatest.response.VehicleResponse;
import com.example.conexatest.conexatest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VehicleViewController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public String getVehiclesView(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(required = false) String name,
                                   Model model) {
        VehicleResponse vehicles = vehicleService.getVehicles(page, size, name);
        model.addAttribute("vehicles", vehicles.getResults());
        model.addAttribute("currentPage", page); // Añadir el número de página actual
        model.addAttribute("totalRecords", vehicles.getTotal_records()); // Añadir el número de página actual
        model.addAttribute("totalPages", vehicles.getTotal_pages()); // Añadir el número de página actual
        model.addAttribute("searchName", name);
        return "vehicles";
    }
}
