package com.example.vaccination.security;

import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee= employeeService.findByUsername(username);
        if (employee == null){
            throw new UsernameNotFoundException("User Not Found!");
        }

        EmployeeSecurity employeeSecurity = new EmployeeSecurity(employee);
        return employeeSecurity;
    }
}
