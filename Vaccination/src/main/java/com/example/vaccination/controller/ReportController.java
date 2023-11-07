package com.example.vaccination.controller;

import com.example.vaccination.model.entity.*;
import com.example.vaccination.service.CustomerService;
import com.example.vaccination.service.NewsServices;
import com.example.vaccination.service.impl.CustomerServiceImpl;
import com.example.vaccination.service.impl.InjectionResultServiceImpl;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import com.example.vaccination.service.impl.VaccineTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private InjectionResultServiceImpl injectionResultService;

    //Report of Schedule
    @GetMapping(value = "/report")
    public String reportresult(Model model) {
        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
        model.addAttribute("newsList", newsList);
        return "reportInjectionResult";
    }

    //Report of Customer
    @GetMapping(value = "/reportcustomer")
    public String reportcustomer(Model model,
                                 @RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                 @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to,
                                 @RequestParam(name="fullName", required = false) String fullName,
                                 @RequestParam(name="address", required = false) String address) {

        List<Customer> customers = customerService.getCustomersWithInjectionCount(from, to, fullName, address); // Use transient to get number of inject by customer_id
        model.addAttribute("customers", customers);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("fullName", fullName);
        model.addAttribute("address", address);


        return "reportCustomer";
    }

    //Report of Vaccine
    @GetMapping(value = "/reportvaccine")
    public String filterVaccinesdf(Vaccine vaccine,
                                   @RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                   @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to,
                                   @RequestParam(name = "vaccineSelection", required = false) String vaccineSelection,
                                   @RequestParam(name="origin", required = false) String origin, Model model) {

        List<Vaccine> vaccineList = vaccineService.findByOptions(from, to, vaccineSelection,origin);
        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
        model.addAttribute("vaccineList", vaccineList);
        model.addAttribute("vaccineTypeList", vaccineTypeList);
        return "reportVaccine";
    }



    //Vaccine 2 search by js
//    @GetMapping("/reportvaccine2")
//    public String filterVaccines(Vaccine vaccine,
//                                 @RequestParam(name = "vaccineSelection", required = false) String vaccineSelection,
//                                 @RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
//                                 @RequestParam(name = "to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date to,
//                                 @RequestParam(name="origin", required = false) String origin, Model model) {
//
//        List<Vaccine> vaccineList = vaccineService.findbyoptional2(vaccineSelection, origin, from, to);
//        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
//        model.addAttribute("vaccineList", vaccineList);
//        model.addAttribute("vaccineTypeList", vaccineTypeList);
//        model.addAttribute("vaccineSelection",vaccineSelection);
////        model.addAttribute("from",from);
////        model.addAttribute("to",to);
////        model.addAttribute("origin", origin);
//
//        return "reportvaccine2";
//    }




}




