package com.example.vaccination.controller;

import com.example.vaccination.mail.EmailSender;
import com.example.vaccination.mail.ForgetCodeTemplate;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.impl.AuthenticationServiceImpl;
import com.example.vaccination.service.VaccineService;
import com.example.vaccination.service.impl.EmployeeServiceImpl;
import com.example.vaccination.token.Token;
import com.example.vaccination.token.TokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import static java.util.regex.Pattern.matches;

@Controller
public class ForgotPasswordController {

    @Autowired
    private AuthenticationServiceImpl service;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailSender gmailSender;

    @GetMapping("/forgot_password_email")
    public String forgotPasswordForm() {
        return "forgot_password";
    }

     // 01-12-2023
    @PostMapping("/forgot_password_form")
    public String proccesingPassword(@RequestParam(value = "email") String email, Model model) {
        try {
            Employee exist = employeeService.findByEmail(email);
            Token checkToken = tokenService.findByEmp(exist);
            if (checkToken != null){
                tokenService.delete(checkToken);
            }
            if (exist != null) {
                String forgetCode = Math.round((Math.random() * 899999 + 100000)) + "";
                System.out.println("Forget Code: " + forgetCode);
                String tokens = forgetCode;
                Token resetToken = Token.builder()
                        .createAt(LocalDateTime.now())
                        .expiredAt(LocalDateTime.now().plusSeconds(60))
                        .value(tokens)
                        .employee(exist)
                        .build();
                tokenService.save(resetToken);
                String template = ForgetCodeTemplate.getTemplete(exist.getUsername(), tokens);
                gmailSender.send(template,exist.getEmail());
                model.addAttribute("email", email);
                model.addAttribute("forgetCode", forgetCode);
                return "forgot_password_form";
            } else {
                model.addAttribute("error", "Email does not exist!!!");
                return "forgot_password";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred.");
            return "forgot_password";
        }
    }

    @GetMapping(path = "/resend_code")
    public String resendCode(@RequestParam(value = "email") String email, Model model){
        try {
            Employee exist = employeeService.findByEmail(email);
            Token checkToken = tokenService.findByEmp(exist);
            if (checkToken != null){
                tokenService.delete(checkToken);
            }
            if (exist != null) {
                String forgetCode = Math.round((Math.random() * 899999 + 100000)) + "";
                System.out.println("Forget Code: " + forgetCode);
                String tokens = forgetCode;
                Token resetToken = Token.builder()
                        .createAt(LocalDateTime.now())
                        .expiredAt(LocalDateTime.now().plusSeconds(60))
                        .value(tokens)
                        .employee(exist)
                        .build();
                tokenService.save(resetToken);
                String template = ForgetCodeTemplate.getTemplete(exist.getUsername(), forgetCode);
                gmailSender.send(template,exist.getEmail());
                model.addAttribute("email", email);
                model.addAttribute("forgetCode", forgetCode);
                return "forgot_password_form";
            } else {
                model.addAttribute("error", "Email does not exist!!!");
                return "forgot_password";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred.");
            return "forgot_password";
        }
    }
    // 01-12-2023



    @PostMapping("/reset/confirm")
    public ResponseEntity<ResetResponse> resetConfirm(@RequestParam("email") String email, @RequestParam("code") String code) {
        return ResponseEntity.ok().body(service.resetConfirm(email, code));
    }

    // 01-12-2023
    @GetMapping(path ="/resetNew")
    public String resetpass(@RequestParam(value = "email") String email, @RequestParam("code") String code,Model model){
        model.addAttribute("email", email);
        model.addAttribute("code", code);
        Employee exist = employeeService.findByEmail(email);
        Token checkToken = tokenService.findByEmp(exist);
        if (!tokenService.isValid(checkToken)){
            tokenService.delete(checkToken);
        }
        return "change-password";
    }

    @PostMapping(path ="/resetNew")
    public String resetPassword(@RequestParam(value = "email") String email,
                                @RequestParam(value = "password") String newPass){
        Employee exist = employeeService.findByEmail(email);
        Token checkToken = tokenService.findByEmp(exist);
        if (newPass != null){
            employeeService.saving(email, newPass);
            tokenService.delete(checkToken);
        }
        return "redirect:/login";
    }
    // 01-12-2023
}