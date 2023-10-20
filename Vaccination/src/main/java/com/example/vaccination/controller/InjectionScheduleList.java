package com.example.vaccination.controller;

import com.example.vaccination.model.entity.InjectionSchedule;
import com.example.vaccination.service.InjectionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InjectionScheduleList {

    @Autowired
    private InjectionScheduleService injectionScheduleService;

    @GetMapping( "/injectionScheduleList")  /*/schedule/list*/
    public String findAll(Model model){
        List<InjectionSchedule> scheduleList = injectionScheduleService.findAllByOrderByInjectionScheduleID();
        model.addAttribute("scheduleList", scheduleList);
        return "injectionScheduleList";
    }

}
