package com.example.vaccination.service;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.request.CheckMsg;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findByEmployeeID(String id);

    List<Employee> findAllByEmployeeName(String name);

    Employee findByUsername(String username);

    CheckMsg create(Employee employee);

    CheckMsg update(Employee employee);

    Employee delete(Employee employee);

    List<Employee> activeEmployeeList();
}
