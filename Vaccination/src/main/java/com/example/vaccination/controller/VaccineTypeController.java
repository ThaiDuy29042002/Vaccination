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

//    @GetMapping("/updateVaccineType/{id}")
    @GetMapping("/updateVaccineType")
    public String showVaccineTypeUpdateForm(@RequestParam("id") String id, Model model) {
        VaccineType existingVaccineType = vaccineTypeService.findById(id);
        model.addAttribute("vaccineType", existingVaccineType);
        return "updateVaccineType";
    }
    @PostMapping(value = "/updateVaccineType")
    public String change(@ModelAttribute("vaccineType") @Valid VaccineType vaccineType, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "updateVaccineType";
        }
        vaccineTypeService.save(vaccineType);
        return "redirect:/vaccineTypeList";
    }
//    @PostMapping(value = "/updateVaccineType/{id}")
//    public String change(@PathVariable String id, @ModelAttribute("vaccineType") @Valid VaccineType vaccineTypeU, BindingResult bindingResult){
//        VaccineType existingVaccineType = vaccineTypeService.findById(id);
//        if (bindingResult.hasErrors()) {
//            return "updateVaccineType";
//        }
//        existingVaccineType.setVaccineTypeID(vaccineTypeU.getVaccineTypeID());
//        existingVaccineType.setVaccineTypeName(vaccineTypeU.getVaccineTypeName());
//        existingVaccineType.setStatus(vaccineTypeU.isStatus());
//        existingVaccineType.setDescription(vaccineTypeU.getDescription());
//        vaccineTypeService.save(existingVaccineType);
//        return "redirect:/vaccineTypeList";
//    }

}
