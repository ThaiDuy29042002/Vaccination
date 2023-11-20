package com.example.vaccination.service.impl;

import com.example.vaccination.controller.ResetResponse;
import com.example.vaccination.mail.EmailSender;
import com.example.vaccination.mail.ForgetCodeTemplate;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.token.Token;
import com.example.vaccination.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

import static java.util.regex.Pattern.matches;

@Service
public class AuthenticationServiceImpl {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private EmailSender gmailSender;

    @Autowired
    private TokenService tokenService;

    public Employee resetFind(@RequestParam("email") String email){
        return employeeService.findByEmail(email);
    }

    public ResetResponse resetSend(String email) throws Exception {
        Employee exist = employeeService.findByEmail(email);
        Token checkToken = tokenService.findByEmp(exist);
        if (checkToken != null) {
            tokenService.delete(checkToken);
        }
        String forgetCode = Math.round((Math.random() * 899999 + 100000)) + "";
        System.out.println("Forget Code: " + forgetCode);
        String tokens = forgetCode;
        Token resetToken = Token.builder()
                .createAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusSeconds(20))
                .value(tokens)
                .employee(exist)
                .build();
        tokenService.save(resetToken);
        String template = ForgetCodeTemplate.getTemplete("FA-projectvippro", exist.getUsername(), forgetCode);
        gmailSender.send("Forgot Password", template, exist.getEmail());
        return ResetResponse.builder()
                .email(exist.getEmail())
                .status("Send confirm code succesfully")
                .build();
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

    public ResetResponse resetNew(String email, String newPass) {
        String status = "Reset Password Succesfully";
        Employee exist = employeeService.findByEmail(email);
        employeeService.saving(email, newPass);
        return ResetResponse.builder()
                .email(exist.getEmail())
                .status(status)
                .build();
    }
}