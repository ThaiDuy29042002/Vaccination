package com.example.vaccination.controller;

import com.example.vaccination.mail.EmailSender;
import com.example.vaccination.mail.ForgetCodeTemplate;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.impl.AuthenticationServiceImpl;
import com.example.vaccination.mail.EmailSender;
import com.example.vaccination.mail.ForgetCodeTemplate;
import com.example.vaccination.model.entity.Employee;
import com.example.vaccination.service.impl.EmployeeServiceImpl;
import com.example.vaccination.token.Token;
import com.example.vaccination.token.TokenService;
import com.example.vaccination.token.Token;
import com.example.vaccination.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

//    @GetMapping(value = {"/login"})
//    public String loginPage(Model model) {return "login";}

    @GetMapping("/forgot_password_email")
    public String forgotPasswordForm() {
        return "forgot_password";
    }

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
                String template = ForgetCodeTemplate.getTemplete("Forgot Password", exist.getUsername(), forgetCode);
                gmailSender.send("Forgot Password", template, exist.getEmail());
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
                String template = ForgetCodeTemplate.getTemplete("Forgot Password", exist.getUsername(), forgetCode);
                gmailSender.send("Forgot Password", template, exist.getEmail());
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

    @PostMapping("/reset/confirm")
    public ResponseEntity<ResetResponse> resetConfirm(@RequestParam("email") String email, @RequestParam("code") String code) {
        return ResponseEntity.ok().body(service.resetConfirm(email, code));
    }

    @PostMapping("/confirm_code")
    public String confirmCode(@RequestParam(value = "email") String email,
                              @RequestParam(value = "code") String code, Model model){
        String status = "Confirm Succesfully";
        Employee exist = employeeService.findByEmail(email);
        Token resetToken = tokenService.findByEmp(exist);
        if(resetToken == null){
            model.addAttribute("status", "out");
            return "forgot_password_form";
        }
        if (!tokenService.isValid(resetToken)) {
            status = "Confirm code is expired!";
            model.addAttribute("email", email);
            model.addAttribute("status", status);
            return "forgot_password_form";
        }
        boolean isMatching = matches(code, resetToken.getValue());
        if (!isMatching) {
            status = "Code isn't match";
            model.addAttribute("email", email);
            model.addAttribute("status", status);
            return "forgot_password_form";
        }
        tokenService.delete(resetToken);
        model.addAttribute("email", email);
        model.addAttribute("status", status);
        return "change-password";
    }


    @GetMapping(path ="/resetNew")
    public String resetpass(@RequestParam(value = "email") String email, Model model){
        model.addAttribute("email", email);
        return "change-password";
    }

    @PostMapping(path ="/resetNew")
    public String resetPassword(@RequestParam(value = "email") String email,
                                @RequestParam(value = "password") String newPass){
        employeeService.saving(email, newPass);
        return "redirect:/login";
    }
}