package com.example.vaccine.repository;

import com.example.vaccine.models.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
