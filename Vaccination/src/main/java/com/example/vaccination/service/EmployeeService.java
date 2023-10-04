package com.example.vaccination.service;

import com.example.vaccination.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee findByUsername(String username);
}
