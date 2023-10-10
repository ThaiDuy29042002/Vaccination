package com.example.vaccination.controller;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.model.entity.InjectionSchedule;
import com.example.vaccination.service.InjectionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class InjectionScheduleList {

    @Autowired
    private InjectionScheduleService injectionScheduleService;

    @GetMapping( "/injectionScheduleList")  /*/schedule/list*/
    public String findAll(Model model){
        List<InjectionSchedule> scheduleList = injectionScheduleService.findAll();
        model.addAttribute("scheduleList", scheduleList);
        return "injectionScheduleList";
    }

}
