package com.example.vaccination.controller;

import com.example.vaccination.service.impl.VaccineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private VaccineServiceImpl vaccineService;

    // Vaccine chart
    @GetMapping("/reportvaccine-chart")
    public String getVaccineChartData(Model model,
                                @RequestParam(name = "yearSelection", required = false) String yearSelect) {
        List<String> vaccineList = vaccineService.count(yearSelect);
        model.addAttribute("yearSelection", yearSelect);
        model.addAttribute("vaccineList",vaccineList);

        return "vaccinechart";
    }


    @GetMapping("/chart2")
    public String getChartData2(Model model,
                                @RequestParam(name = "yearSelection", required = false) String yearSelect) {
        List<String> vaccineList = vaccineService.count(yearSelect);
        model.addAttribute("yearSelection", yearSelect);
        model.addAttribute("vaccineList",vaccineList);

        return "chart2";
    }


    // Customers chart
//    @GetMapping("/reportcustomer-chart")
//    public String getCustomerChartData(Model model, @RequestParam(name="")){
//
//    }


}