package com.example.vaccination;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.EmployeeRepository;
import com.example.vaccination.service.EmployeeService;
import com.example.vaccination.service.impl.EmployeeServiceImpl;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import com.example.vaccination.service.impl.VaccineTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaccinationApplication implements CommandLineRunner {

    @Autowired
    VaccineTypeServiceImpl vaccineTypeService;

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

        VaccineType vaccineType= new VaccineType();
        vaccineType.setVaccineTypeID("VT41256");
        vaccineType.setStatus(true);
        vaccineType.setVaccineTypeName("ABC");
        vaccineType.setDescription("null");
        vaccineTypeService.save(vaccineType);

        VaccineType vaccineType2= new VaccineType();
        vaccineType2.setVaccineTypeID("VT41250");
        vaccineType2.setStatus(true);
        vaccineType2.setVaccineTypeName("DEF");
        vaccineType2.setDescription("null");
        vaccineTypeService.save(vaccineType2);

    }
}
