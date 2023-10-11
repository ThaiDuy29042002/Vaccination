package com.example.vaccination.controller;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.request.CheckMsg;
import com.example.vaccination.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employee")
    private String employeePage(Model model){
        List<Employee> employeeList = employeeService.activeEmployeeList();
        model.addAttribute("employeeList", employeeList);
        return "employeeList";
    }

    @GetMapping(value = "/createemp")
    private String createPage(Model model){
        Employee emp = new Employee();
        model.addAttribute("emp", emp);
        return "createEmployee";
    }

    @GetMapping(value = "/updateemp")
    private String updatePage(Model model, @RequestParam String id){
        Employee emp = employeeService.findByEmployeeID(id);
        model.addAttribute("emp",emp);
        return "updateEmployee";
    }

    @PostMapping(value = "/createemp")
    private String createNewEmployee(@ModelAttribute("emp") Employee emp, @RequestParam String sdate, Model model){
        Date date = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(sdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        emp.setDateOfBirth(date);
        CheckMsg createEmp = employeeService.create(emp);
        model.addAttribute("msg",createEmp.getMsg());
        if(createEmp.getEmp() == null){
            Employee emp1 = new Employee();
            model.addAttribute("emp", emp1);
            return "createEmployee";
        }
        return employeePage(model);
    }

    @PostMapping(value = "/updateemp")
    private String updateEmployee(@ModelAttribute("emp") Employee emp, @RequestParam String sdate, Model model){
        Date date = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(sdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        emp.setDateOfBirth(date);
        String id = emp.getEmployeeID();
        CheckMsg createEmp = employeeService.update(emp);
        model.addAttribute("msg",createEmp.getMsg());
        if(createEmp.getEmp() == null){
            Employee emp1 = new Employee();
            model.addAttribute("emp", emp1);
            return updatePage(model, id);
        }
        return employeePage(model);
    }

    @GetMapping(value = "/deleteemp")
    private String deleteEmployee(Model model, @RequestParam String id){
        employeeService.delete(employeeService.findByEmployeeID(id));
        model.addAttribute("msg","Deleted Success!!");
        return employeePage(model);
    }
}
