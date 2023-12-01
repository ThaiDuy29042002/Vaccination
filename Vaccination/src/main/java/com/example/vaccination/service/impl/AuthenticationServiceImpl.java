package com.example.vaccination.service.impl;

import com.example.vaccination.controller.ResetResponse;
import com.example.vaccination.mail.EmailSender;
import com.example.vaccination.mail.ForgetCodeTemplate;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.token.Token;
import com.example.vaccination.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

import static java.util.regex.Pattern.matches;

@Service
public class AuthenticationServiceImpl {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private JavaMailSender gmailSender;

    @Autowired
    private TokenService tokenService;

    public Employee resetFind(@RequestParam("email") String email){
        return employeeService.findByEmail(email);
    }

    public ResetResponse resetConfirm(String email, String code) {
        String status = "Confirm Succesfully";
        Employee exist = employeeService.findByEmail(email);
        Token resetToken = tokenService.findByEmp(exist);
        if (!tokenService.isValid(resetToken)) {
            status = "Confirm code is expired!";
        }
        boolean isMatching = matches(code, resetToken.getValue());
        if (!isMatching) {
            status = "Code isn't match";
        }
        return ResetResponse.builder()
                .email(exist.getEmail())
                .status(status)
                .build();
    }
}