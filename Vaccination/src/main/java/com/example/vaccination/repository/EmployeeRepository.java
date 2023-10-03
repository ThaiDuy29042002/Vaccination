package com.example.vaccination.repository;

import com.example.vaccination.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Override
    List<Employee> findAll();

    Employee findByEmployeeID(String id);

    List<Employee> findAllByEmployeeName(String name);


}
