package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.repository.EmployeeRepository;
import com.example.vaccination.service.EmployeeService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmployeeID(String id) {
        return employeeRepository.findByEmployeeID(id);
    }

    @Override
    public List<Employee> findAllByEmployeeName(String name) {
        return employeeRepository.findAllByEmployeeName(name);
    }

    @Override
    public Employee save(Employee employee) {
        String hashedPassword = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt());
        employee.setPassword(hashedPassword);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }
}
