package com.example.vaccination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
    @GetMapping(value = "/")
    public String home(){
        return "homePage";
    }
    @GetMapping(value = "/vaccineList")
    public String vaccineList(){
        return "vaccineList";}

//    @GetMapping(value = "/login")
//    public String (){
//        return "/login";}

    @GetMapping(value = "/newsList")
    public String newsList(){
        return "newsList";
    }

    @GetMapping(value = "/createNews")
    public String createNews(){
        return "createNews";
    }


}
