package com.example.vaccination.controller;

import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VaccineTypeController {


    @Autowired
    private VaccineTypeService vaccineTypeService;


    @GetMapping(value = "/vaccineTypeList")
    public String listVaccineType(Model model){
        List<VaccineType> vaccineType = vaccineTypeService.findAll();
        model.addAttribute("vaccineType", vaccineType);
        return "vaccineTypeList";
    }

    @GetMapping("/createVaccineType")
    public String showVaccineTypeForm(Model model) {
        model.addAttribute("vaccineType", new VaccineType());
        return "createVaccineType";
    }

    @PostMapping(value = "/createVaccineType")
    public String summit(Model model, @ModelAttribute("vaccineType") @Valid VaccineType vaccineType, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "createVaccineType";
        }
        vaccineTypeService.save(vaccineType);
        return "redirect:/vaccineTypeList";
    }

    @GetMapping("/updateVaccineType/{id}")
    public String showVaccineTypeUpdateForm(Model model, @PathVariable String id) {
        VaccineType existingVaccineType = vaccineTypeService.findById(id);
        model.addAttribute("vaccineType", existingVaccineType);
        return "createVaccineType";
    }
    @PostMapping(value = "/updateVaccineType/{id}")
    public String change(Model model, @PathVariable String id, @ModelAttribute("vaccineType") @Valid VaccineType vaccineType, BindingResult bindingResult){
        VaccineType existingVaccineType = vaccineTypeService.findById(id);
        if (bindingResult.hasErrors()) {
            return "createVaccineType";
        }
        existingVaccineType.setVaccineTypeID(vaccineType.getVaccineTypeID());
        existingVaccineType.setVaccineTypeName(vaccineType.getVaccineTypeName());
        existingVaccineType.setStatus(vaccineType.isStatus());
        existingVaccineType.setDescription(vaccineType.getDescription());
        vaccineTypeService.save(existingVaccineType);
        return "redirect:/vaccineTypeList";
    }

}
