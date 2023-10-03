package com.example.vaccination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String showHomepage(Model model) {
        return "homepage";
    }

    @GetMapping(value = {"/access-denied"})
    public String accessDenied(Model model) {
        return "access-denied";
    }

}