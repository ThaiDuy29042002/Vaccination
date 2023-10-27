package com.example.vaccination.controller;

import com.example.vaccination.model.entity.News;
import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import com.example.vaccination.service.impl.VaccineTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class ReportController {
    @Autowired
    private VaccineServiceImpl vaccineService;

    @Autowired
    private VaccineTypeServiceImpl vaccineTypeService;

//    @GetMapping(value = "/report")
//    public String reportcustomer(){
////        NewsServices.findbyId(id);
////        List<News> reportlist = NewsServices.findAllByOrderByPostdateDesc();
////        model.addAttribute("reportlist", reportlist);
//        return "report";
//    }

    @GetMapping(value = "/report")
    public String reportresult(Model model) {
//        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
//        model.addAttribute("newsList", newsList);
        return "reportInjectionResult";
    }

//    @GetMapping(value = "/reportcustomer")
//    public String reportcustomer(Model model) {
//        List<News> newsList = NewsServices.findAllByOrderByPostdateDesc();
//        model.addAttribute("newsList", newsList);
//        return "reportCustomer";
//    }

//    @GetMapping(value = "/reportvaccine")
//    public String reportvaccine(Model model) {
////        List<News> vaccinelist = ;
////        model.addAttribute("vaccinelist", vaccinelist);
//        return "reportVaccine";
//    }

    @GetMapping(value = "/chart")
    public String chart(Model model) {
        return "/chart";
    }

    @GetMapping(value = "/reportvaccine")
    public String findAllcustomer(Model model) {
        List<Vaccine> vaccineList = vaccineService.getAllProducts();
        model.addAttribute("vaccineList", vaccineList);
        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
        model.addAttribute("vaccineTypeList", vaccineTypeList);
        return "reportVaccine";
    }


    //        List<Vaccine> vaccineList = vaccineService.getAllProducts();
//        model.addAttribute("vaccineList", vaccineList);
//        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
//        model.addAttribute("vaccineTypeList", vaccineTypeList);
//        }

//    @GetMapping( "/reportvaccine2")
//    public String findAllcustomerdf(Model model)
//    {
//        List<Vaccine> vaccineList = vaccineService.getAllProducts();
//        List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
//        model.addAttribute("vaccineList", vaccineList);
//        model.addAttribute("vaccineTypeList", vaccineTypeList);
////        model.addAttribute("from",from);
//        return "reportVaccine2";
//    }

    @GetMapping( "/reportvaccine2")
    public String findAllcustomer2(Model model,
                                   @RequestParam(value = "from", defaultValue = "2023-10-25")
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date from)
    {

            List<Vaccine> vaccineList = vaccineService.findByTimeBeginNextInjection(from);
            List<VaccineType> vaccineTypeList = vaccineTypeService.findAll();
            model.addAttribute("vaccineList", vaccineList);
            model.addAttribute("vaccineTypeList", vaccineTypeList);
            model.addAttribute("from",from);
        return "reportVaccine2";

    }




}




