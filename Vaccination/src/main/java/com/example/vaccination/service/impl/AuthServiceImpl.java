package com.example.vaccination.service.impl;


import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.model.request.AuthRequest;
import com.example.vaccination.model.request.LoginRequest;
import com.example.vaccination.model.request.RegisterRequest;
import com.example.vaccination.model.response.AuthResponse;
import com.example.vaccination.security.JwtToken;
import com.example.vaccination.security.UserSecurity;
import com.example.vaccination.service.AuthService;
import com.example.vaccination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // new User
        Employee employee = new Employee();
        employee.setEmployeeID(request.getEmpid());
        employee.setEmail(request.getEmail());
        employee.setAddress(request.getAddress());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setPhone(request.getPhone());
        employee.setEmployeeName(request.getEmployeeName());
        employee.setUsername(request.getUsername());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setPosition("EMPLOYEE");
        employee.setStatus(true);

        // Save
        employeeService.save(employee);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(request.getUsername());

        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Employee employee = employeeService.findByUsername(request.getUsername());
        if (employee != null) {
            UserSecurity userSecurity = new UserSecurity(employee);

            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("username", employee.getUsername());
            extraClaims.put("authorities", userSecurity.getAuthorities());

            String accessToken = jwtToken.generateToken(extraClaims, userSecurity);
            String refreshToken = jwtToken.generateRefreshToken(userSecurity);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setUsername(request.getUsername());
            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);

            return authResponse;
        }
        return null;
    }

    @Override
    public AuthResponse refreshToken(AuthRequest request) {
        return null;
    }

}