package com.example.vaccination.service;

import com.example.vaccination.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findByEmployeeID(String id);

    List<Employee> findAllByEmployeeName(String name);

    Employee findByUsername(String username);

    Employee save(Employee employee);

    Employee update(Employee employee);
}
