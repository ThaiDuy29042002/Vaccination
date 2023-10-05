package com.example.vaccination.controller;

import com.example.vaccination.exception.ApplicationException;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.request.LoginRequest;
import com.example.vaccination.model.response.AuthResponse;
import com.example.vaccination.service.AuthService;
import com.example.vaccination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class Test {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AuthService authService;
    @GetMapping(value = "/hi")
    public String home(Model model) throws ParseException {
        //List<Employee> employees = employeeService.findAll();
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = formatter.parse("2002-04-07");
        //employeeService.save(new Employee("EM001","here",date,"cuong@gmail.com","Cuong",true,"avatar.png","12345","0352312321","admin","admin","tp1",true));
        //employeeService.save(new Employee("EM002","here",date,"cuongne@gmail.com","Kuong",true,"avatar.png","12345","0352312321","admin","admin1","tp1",true));
        //Employee emp = employeeService.findByEmployeeID("EM002");

        //model.addAttribute("empList", employees);

//        try {
//            AuthResponse authResponse = authService.login(new LoginRequest("admin","12345"));
//            if(authResponse == null) return "error";
//            else return "testUI";
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new ApplicationException();
//        }


        try {
            AuthResponse authResponse = authService.login(new LoginRequest("admin","12345"));
            if(authResponse != null) {
                Employee emp = employeeService.findByUsername("admin");
                model.addAttribute("emp",emp);
                return "testUI";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException();
        }
        return "error";
    }

   // @GetMapping(value = {"/ui","auth/ui"})
   // public String testUi(){
   //     return "employeeList";
   // }
}
