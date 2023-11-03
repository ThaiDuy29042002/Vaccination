package com.example.vaccination.controller;

import com.example.vaccination.model.entity.News;
import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.service.NewsServices;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import com.example.vaccination.service.impl.VaccineTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.*;

@Controller
public class ReportController {
    @Autowired
    private VaccineServiceImpl vaccineService;

    @Autowired
    private VaccineTypeServiceImpl vaccineTypeService;

    @Autowired
    private com.example.vaccination.service.NewsServices NewsServices;

//    @GetMapping(value = "/reportcustomer")
//    public String reportcustomer(String id, Model model){
//        NewsServices.findbyId(Integer.parseInt(id));
//        List<News> reportlist = NewsServices.findAllByOrderByPostdateDesc();
//        model.addAttribute("reportlist", reportlist);
//        return "report";
//    }

    @GetMapping(value = "/report")
    public String reportresult(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "reportInjectionResult";
    }

    @GetMapping(value = "/reportcustomer")
    public String reportcustomer(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "reportCustomer";
    }

    @GetMapping(value = "/reportvaccine")
    public String filterVaccinesdf(Vaccine vaccine,
                                   @RequestParam(name = "vaccineSelection", required = false) String vaccineSelection,
                                   @RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                   @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to,
                                   @RequestParam(name="origin", required = false) String origin, Model model) {

        List<Vaccine> vaccineList = vaccineService.findbyoptional2(vaccineSelection,origin, from, to);
        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
        model.addAttribute("vaccineList", vaccineList);
        model.addAttribute("vaccineTypeList", vaccineTypeList);
        return "reportVaccine";
    }



    //Vaccine 2 search by js
    @GetMapping("/reportvaccine2")
    public String filterVaccines(Vaccine vaccine,
                                 @RequestParam(name = "vaccineSelection", required = false) String vaccineSelection,
                                 @RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                 @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to,
                                 @RequestParam(name="origin", required = false) String origin, Model model) {

        List<Vaccine> vaccineList = vaccineService.findbyoptional2(vaccineSelection, origin, from, to);
        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
        model.addAttribute("vaccineList", vaccineList);
        model.addAttribute("vaccineTypeList", vaccineTypeList);
        model.addAttribute("vaccineSelection",vaccineSelection);
//        model.addAttribute("from",from);
//        model.addAttribute("to",to);
//        model.addAttribute("origin", origin);

        return "reportvaccine2";
    }




}




