package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.request.CheckMsg;
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
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public CheckMsg create(Employee employee) {
        String hashedPassword = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt());
        employee.setPassword(hashedPassword);
        employee.setStatus(true);
        Employee existEmp = new Employee();
        existEmp = employeeRepository.findByEmployeeID(employee.getEmployeeID());
        if(existEmp != null) return new CheckMsg(null,"Employee id is exist!!");
        existEmp = employeeRepository.findByUsername(employee.getUsername());
        if(existEmp != null) return new CheckMsg(null,"Username is exist!!");
        existEmp = employeeRepository.findByPhone(employee.getPhone());
        if(existEmp != null) return new CheckMsg(null,"Phone is exist!!");
        existEmp = employeeRepository.findByEmail(employee.getEmail());
        if(existEmp != null) return new CheckMsg(null,"Email is exist!!");
        return new CheckMsg(employeeRepository.saveAndFlush(employee),"Created Successfully!!!");
    }

    @Override
    public Employee delete(Employee employee) {
        employee.setStatus(false);
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public CheckMsg update(Employee employee) {
        Employee duplicateEmp = employeeRepository.findDuplicatePhone(employee.getEmployeeID(),employee.getPhone());
        if(duplicateEmp != null) return new CheckMsg(null,"Phone is duplicated!!");
        duplicateEmp = employeeRepository.findDuplicateEmail(employee.getEmployeeID(),employee.getEmail());
        if(duplicateEmp != null) return new CheckMsg(null,"Email is duplicated!!");
        return new CheckMsg(employeeRepository.saveAndFlush(employee),"Updated Successfully!!!");
    }

    @Override
    public List<Employee> activeEmployeeList() {
        return employeeRepository.findAllByStatusIsTrue();
    }
}
