package com.example.vaccination.controller;

import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/makeInactiveVaccineTypes")
    public String makeInactiveVaccineTypes(@RequestParam("makeInactiveVaccineTypeIds") String vaccineTypeIds) {
        if (vaccineTypeIds.isEmpty()) {
            // No data selected
            return "redirect:/vaccineTypeList?message=No data to make inactive!";
        }

        boolean hasActiveVaccineTypes = false;
            VaccineType vaccineTypeOptional = vaccineTypeService.findById(vaccineTypeIds);
            if (vaccineTypeOptional != null) {
                if (vaccineTypeOptional.isStatus()) {
                    // If it's already active, set it to inactive
                    vaccineTypeOptional.setStatus(false);
                    vaccineTypeService.save(vaccineTypeOptional);
                } else {
                    // If it's already inactive, set a flag
                    hasActiveVaccineTypes = true;
                }
            }

        if (hasActiveVaccineTypes) {
            // Some selected vaccine types were already inactive
            return "redirect:/vaccineTypeList?message=Invalid data - please recheck your selects!";
        } else {
            // All selected vaccine types were successfully made inactive
            return "redirect:/vaccineTypeList?message=Selected vaccine type(s) made In-Active!";
        }
    }
    @DeleteMapping(value = "/VaccineTypeList/delete")
    @ResponseBody
    public ResponseEntity<String> categoryDelete(@RequestParam(value = "id", required = false) String vaccineTypeId) {
        VaccineType vaccineType = vaccineTypeService.findById(vaccineTypeId);
        if (vaccineType == null) {
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        vaccineType.setStatus(false);
        vaccineTypeService.save(vaccineType);
        return new ResponseEntity<>("OK", HttpStatus.OK);
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
