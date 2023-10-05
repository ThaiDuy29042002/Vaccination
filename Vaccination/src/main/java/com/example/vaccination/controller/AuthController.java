package com.example.vaccination.controller;

import com.example.vaccination.exception.ApplicationException;
import com.example.vaccination.model.request.LoginRequest;
import com.example.vaccination.model.request.RegisterRequest;
import com.example.vaccination.model.response.AuthResponse;
import com.example.vaccination.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@ModelAttribute("loginrequest") LoginRequest request) {
        try {
            AuthResponse authResponse = authService.login(request);
            if(authResponse != null)  return "homePage";
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException();
        }
        return "error";
    }
    @GetMapping(value = {"/login"})
    public String loginPage(Model model) {
        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("loginrequest", loginRequest);
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                request.getSession().invalidate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/login?logout";
    }
}