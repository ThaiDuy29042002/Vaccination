package com.example.vaccination.controller;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.request.CheckMsg;
import com.example.vaccination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employee")
    private String employeePage(Model model, @RequestParam(required = false) String msgW, @RequestParam(required = false) String msgS){
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("msgS", msgS);
        model.addAttribute("msgW", msgW);
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
        if(ids == null)             return "redirect:/employee?msgW=Must choose 1 employee to update!!!";
        else if(ids.length != 1)    return "redirect:/employee?msgW=Just choose 1 employee to update!!!";
        else{
            Employee emp = employeeService.findByEmployeeID(Arrays.stream(ids).toList().get(0));
            model.addAttribute("emp",emp);
            return "updateEmployee";
        }
    }


    @PostMapping(value = "/createemp")
    private String createNewEmployee(@ModelAttribute("emp") Employee emp, @RequestParam(value = "imgFile", required = false) MultipartFile image, @RequestParam String sdate, Model model){
        Date date = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(sdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Path path = Paths.get("src/main/resources/static/vendors/img1");
        try{
            String newName = emp.getEmployeeID()+".jpg";
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, path.resolve(newName), StandardCopyOption.REPLACE_EXISTING);
            emp.setImage(newName.toLowerCase());
        }catch (Exception e){
            e.printStackTrace();
        }
        emp.setDateOfBirth(date);
        CheckMsg createEmp = employeeService.create(emp);
        //model.addAttribute("msg",createEmp.getMsg());
        if(createEmp.getEmp() == null){
            Employee emp1 = new Employee();
            model.addAttribute("emp", emp1);
            return "createEmployee?msgW="+createEmp.getMsg();
        }
        return "redirect:/employee?msg="+createEmp.getMsg();
    }

    @PostMapping(value = "/updateemp")
    private String updateEmployee(@ModelAttribute("emp") Employee emp, @RequestParam(value = "imgFile", required = false) MultipartFile image, @RequestParam("oldImg") String oldImg, @RequestParam String sdate, Model model){
        Date date = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(sdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(image.getSize() == 0) emp.setImage(oldImg);
        else{
            Path path = Paths.get("src/main/resources/static/vendors/img1/");
            try{
                String newName = emp.getEmployeeID()+".jpg";
                InputStream inputStream = image.getInputStream();
                Files.copy(inputStream, path.resolve(newName), StandardCopyOption.REPLACE_EXISTING);
                emp.setImage(newName.toLowerCase());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        emp.setDateOfBirth(date);
        String id = emp.getEmployeeID();
        CheckMsg createEmp = employeeService.update(emp);
        model.addAttribute("msgW",createEmp.getMsg());
        if(createEmp.getEmp() == null){
            Employee emp1 = new Employee();
            List<String> ids = new ArrayList<>();
            ids.add(id);
            model.addAttribute("emp", emp1);
            return updatePage(model, ids.toArray(new String[0]));
        }
        return "redirect:/employee?msgS="+createEmp.getMsg();
    }

    @GetMapping(value = "/deleteemployee")
    private String deleteEmployee(Model model, @RequestParam String[] ids){
        if(ids.length == 0)  return "redirect:/employee?msgW=Choose at least 1 to delete!!";
        boolean isWrong = false;
        String wrongId = "";
        for (String id : ids
        ) {
            if(!employeeService.findByEmployeeID(id).isStatus()){
                wrongId = id+" is inactive!!";
                isWrong = true;
                break;
            }
        }
        if(!isWrong) {
            for (String id : ids)   employeeService.delete(employeeService.findByEmployeeID(id));
            return "redirect:/employee?msgS=Deleted Success!!";
        }
        return "redirect:/employee?msgW=Id: "+wrongId;
    }
}
