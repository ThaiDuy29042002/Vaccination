package com.example.vaccination;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.repository.EmployeeRepository;
import com.example.vaccination.service.EmployeeService;
import com.example.vaccination.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaccinationApplication implements CommandLineRunner {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(VaccinationApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Employee employee =new Employee();
        employee.setEmployeeID("EM5656");
        employee.setAddress("thu duc");
        employee.setEmail("khoahd@gg.com");
        employee.setEmployeeName("admin");
        employee.setGender(true);
        employee.setPassword("123");
        employee.setPhone("0905874659");
        employee.setPosition("ADMIN");
        employee.setUsername("admin");
        employee.setStatus(true);
        employeeService.save(employee);

    }
}
