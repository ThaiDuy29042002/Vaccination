package com.example.vaccination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    @GetMapping(value = "/employee")
    private String employeePage(){
        return "employeeList";
    }

}
