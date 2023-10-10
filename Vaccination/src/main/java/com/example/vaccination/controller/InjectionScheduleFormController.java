package com.example.vaccination.controller;

import com.example.vaccination.exception.NotFoundException;
import com.example.vaccination.handler.ScheduleMap;
import com.example.vaccination.model.dto.InjectionScheduleDto;
import com.example.vaccination.model.entity.InjectionSchedule;
import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.repository.InjectionScheduleRepository;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.service.InjectionScheduleService;
import com.example.vaccination.service.VaccineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InjectionScheduleFormController {

    @Autowired
    private InjectionScheduleService injectionScheduleService;

    @Autowired
    private InjectionScheduleRepository injectionScheduleRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @GetMapping("/createInjectionSchedule")
    public String showCreateFormSchedule(Model model, RedirectAttributes red){
        List<InjectionSchedule> scheduleList = injectionScheduleService.findAll();
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        model.addAttribute("vaccineList",vaccineList);
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("injectionSchedule", new InjectionSchedule());
        model.addAttribute("pageTitle","Create New Injection Schedule");
        red.addFlashAttribute("pageTitle","Create New Injection Schedule");

          return "createInjectionSchedule";
    }


    @PostMapping("/saveIS")
    public String saveSchedule(@ModelAttribute("injectionSchedule") @Valid InjectionScheduleDto injectionScheduleDto,
                               RedirectAttributes red, Model model) throws ParseException {
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        model.addAttribute("vaccineList",vaccineList);
        InjectionSchedule injectionSchedule = ScheduleMap.INSTANCE.stringToDate(injectionScheduleDto);
        if (injectionSchedule.getEndDate().before(injectionSchedule.getStartDate())) {
            if(injectionSchedule.getInjectionScheduleID() !=0){
                model.addAttribute("pageTitle","Update Injectrion Schedule");
                model.addAttribute("error", "From date must be less than to date");
                return "/createInjectionSchedule";
            }else {
                model.addAttribute("pageTitle" , "Create New Injection Schedule");
                model.addAttribute("error", "From date must be less than to date");
                return "/createInjectionSchedule";
            }
        }
        Vaccine vaccine = vaccineRepository.findById(injectionSchedule.getVaccine_s().getVaccineID()).orElse(null);
        injectionSchedule.setVaccine_s(vaccine);
        if(injectionSchedule.getStatus() == 0){
            injectionSchedule.setStatus(2);
            injectionScheduleService.save(injectionSchedule);
            red.addFlashAttribute("message", "Saved Sucessfull !!!");
            return "redirect:/injectionScheduleList";
        }
        injectionScheduleService.save(injectionSchedule);
        red.addFlashAttribute("message", "Saved Sucessfull !!!");
        return "redirect:/injectionScheduleList";
    }


    @GetMapping("/updateIS")
    public String updateScheduleWithCheckBox(@RequestParam int injectrionScheduleID,String selectedIDs,
                                             Model model, RedirectAttributes red) {
        try {
            /* update with check box -------------------------------------------------------- */
            if(selectedIDs != null){
                /*String id = selectedIDs;
                    int scheduleID =   Integer.parseInt(id);*/
                InjectionSchedule injectionSchedule = injectionScheduleService.get(injectrionScheduleID);
                if (injectionSchedule.getEndDate().before(injectionSchedule.getStartDate())) {
                    model.addAttribute("error", "From date must be less than to date");
                    return "/createInjectionSchedule/save";
                }
                List<Vaccine> vaccineList = vaccineRepository.findAll();

                model.addAttribute("vaccineList",vaccineList);
                model.addAttribute("injectionSchedule", injectionSchedule);
                model.addAttribute("pageTitle", "Update Injectrion Schedule ");
                red.addFlashAttribute("pageTitle", "Update Injectrion Schedule ");
                return "createInjectionSchedule";
            }
            else {
                /* update at action -------------------------------------------------------------- */
                InjectionSchedule injectionSchedule = injectionScheduleService.get(injectrionScheduleID);
                if (injectionSchedule.getEndDate().before(injectionSchedule.getStartDate())) {
                    model.addAttribute("error", "From date must be less than to date");
                    return "/createInjectionSchedule/save";
                }
                List<Vaccine> vaccineList = vaccineRepository.findAll();
                model.addAttribute("vaccineList", vaccineList);
                model.addAttribute("injectionSchedule", injectionSchedule);
                model.addAttribute("pageTitle", "Update Injectrion Schedule ");
                red.addFlashAttribute("pageTitle", "Update Injectrion Schedule ");

                return "createInjectionSchedule";
            }
        } catch (NotFoundException e) {
            red.addFlashAttribute("message", e.getMessage());
            return "redirect:/injectionScheduleList";
        }
    }

}
