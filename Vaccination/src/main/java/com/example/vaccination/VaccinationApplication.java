package com.example.vaccination;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;

import java.util.Optional;

public class VaccinationApplication {
    public static void main(String[] args) { SpringApplication.run(VaccinationApplication.class, args); }


}
