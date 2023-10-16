package com.example.vaccination.controller;

import com.example.vaccination.model.entity.Customer;
import com.example.vaccination.model.entity.InjectionResult;
import com.example.vaccination.repository.CustomerRepository;
import com.example.vaccination.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InjectionResultController {
    //@Autowired
    //InjectionResultService injectionResultService;

    @Autowired
    CustomerRepository customerRepository;
    @GetMapping("/injectionresult")
    private String injectionresultpage(Model model){
        //List<InjectionResult> injectionResultList = injectionResultService.findAll();
        //model.addAttribute("injectionResultList", injectionResultList);
        return "injectionResultList";
    }

    @GetMapping("/createinjectionresult")
    private String createinjectionresultpage(Model model){
        InjectionResult creinjection = new InjectionResult();
        List<Customer> customerList = customerRepository.findAll();
        model.addAttribute("creinjection", creinjection);
        model.addAttribute("customerList", customerList);
        return "createInjectionResult";
    }

}

