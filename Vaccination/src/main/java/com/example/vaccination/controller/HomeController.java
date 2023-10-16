package com.example.vaccination.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/home"})
    //@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public String showHomepage(Model model, Authentication authentication) {
        return "homePage";
    }

    @GetMapping(value = {"/access-denied"})
    public String accessDenied(Model model) {
        return "access-denied";
    }

}