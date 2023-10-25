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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employee")
    private String employeePage(Model model, @RequestParam(required = false) String msg){
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("msg", msg);
        return "employeeList";
    }

    @GetMapping(value = "/createemp")
    private String createPage(Model model){
        Employee emp = new Employee();
        model.addAttribute("emp", emp);
        return "createEmployee";
    }

    @GetMapping(value = "/updateemp")
    private String updatePage(Model model, @RequestParam(value = "checkedid", required = false) String[] ids){
        if(ids == null){
            model.addAttribute("msg","Must choose 1 employee to update!!!");
            //return employeePage(model);

            return "redirect:/employee?msg=Must choose 1 employee to update!!!";
        }
        else if(ids.length != 1){
            model.addAttribute("msg","Just choose 1 employee to update!!!");
//            return employeePage(model);
            return "redirect:/employee?msg=Just choose 1 employee to update!!!";
        }else{
            Employee emp = employeeService.findByEmployeeID(Arrays.stream(ids).toList().get(0));
            model.addAttribute("emp",emp);
            return "updateEmployee";
        }


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
        //model.addAttribute("msg",createEmp.getMsg());
        if(createEmp.getEmp() == null){
            Employee emp1 = new Employee();
            model.addAttribute("emp", emp1);
            return "createEmployee";
        }
        //return employeePage(model);
        return "redirect:/employee?msg="+createEmp.getMsg();
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
            List<String> ids = new ArrayList<>();
            ids.add(id);
            model.addAttribute("emp", emp1);
            return updatePage(model, ids.toArray(new String[0]));
        }
        return "redirect:/employee?msg="+createEmp.getMsg();
//        return employeePage(model);
    }

    @GetMapping(value = "/deleteemployee")
    private String deleteEmployee(Model model, @RequestParam String[] ids){
        for (String id : ids
        ) {
            employeeService.delete(employeeService.findByEmployeeID(id));
        }
        model.addAttribute("msg", "Deleted Success!!");
        //return employeePage(model);
        return "redirect:/employee?msg=Deleted Success!!";
    }
}
