package com.example.vaccination.controller;

import com.example.vaccination.model.entity.Customer;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.service.CustomerService;
import com.example.vaccination.service.EmployeeService;
import com.example.vaccination.service.VaccineService;
import com.example.vaccination.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Test {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    VaccineTypeService vaccineTypeService;
    @Autowired
    VaccineService vaccineService;
    @GetMapping(value = "/hi")
    public String home(Model model) throws ParseException {
        //List<Employee> employees = employeeService.findAll();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("2002-04-07");
        Date dates = formatter.parse("2024-04-07");
        employeeService.save(new Employee("EM001","here",date,"cuong@gmail.com","Cuong",true,"avatar.png","12345","0352312321","admin","admin","tp1",true));
        //employeeService.save(new Employee("EM002","here",date,"cuongne@gmail.com","Kuong",true,"avatar.png","12345","0352315551","employee","cuong","tp1",true));
        customerService.save(new Customer(1,"saigon",date,"tranvana@gmail.com","Tran Van A",true,"12345678910","12345","0987654321","tranvana"));
        Employee emp = employeeService.findByEmployeeID("EM002");
//        vaccineTypeService.save(new VaccineType(1,"des 1","Vaccine 1",true));
//        vaccineService.save(new Vaccine("1","No","no",2,"Vietnam",date,dates,"des 1","Covid Vaccine",vaccineTypeService.findById(1),true));

        //model.addAttribute("empList", employees);

//        try {
//            AuthResponse authResponse = authService.login(new LoginRequest("admin","12345"));
//            if(authResponse == null) return "error";
//            else return "testUI";
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new ApplicationException();
//        }


//        try {
//            AuthResponse authResponse = authService.login(new LoginRequest("admin","12345"));
//            if(authResponse != null) {
//                Employee emp = employeeService.findByUsername("admin");
             model.addAttribute("emp",emp);
//                return "testUI";
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new ApplicationException();
//        }
//        return "error";


   // @GetMapping(value = {"/ui","auth/ui"})
   // public String testUi(){
        return "testUI";
    }
}
