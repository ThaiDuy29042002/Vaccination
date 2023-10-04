package com.example.vaccination;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;

import java.util.Optional;
@SpringBootApplication
public class VaccinationApplication implements  CommandLineRunner{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeRepository employeeRepository;


    public static void main(String[] args) { SpringApplication.run(VaccinationApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        String pass = passwordEncoder.encode("123456");
        Employee employee =new Employee();
        employee.setEmployeeID("EM9999");
        employee.setAddress("thu duc");
        employee.setDateOfBirth("55-665-55");
        employee.setEmail("ok");
        employee.setGender(true);
        employee.setPhone("0354756789");
        employee.setEmployeeName("admin");
        employee.setUsername("admin");
        employee.setPassword(pass);
        employee.setPosition("ADMIN");

        employeeRepository.saveAndFlush(employee);
    }
}
