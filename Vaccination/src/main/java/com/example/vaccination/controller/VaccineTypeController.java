package com.example.vaccination.controller;

import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import com.example.vaccination.validator.VaccineTypeValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class VaccineTypeController {


    @Autowired
    private VaccineTypeService vaccineTypeService;

    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;

    @Autowired
    private VaccineTypeValidator vaccineTypeValidator;


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
        vaccineTypeValidator.validate(vaccineType,bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("vaccineType",vaccineType);
            return "createVaccineType";
        }
        vaccineTypeService.save(vaccineType);
        return "redirect:/vaccineTypeList";
    }

    @GetMapping("/updateVaccineType")
    public String showVaccineTypeUpdateForm(@RequestParam("id") String id, Model model) {
        VaccineType existingVaccineType = vaccineTypeService.findById(id);
        model.addAttribute("vaccineType", existingVaccineType);
        return "updateVaccineType";
    }
    @PostMapping(value = "/updateVaccineType")
    public String change(Model model,@ModelAttribute("vaccineType") @Valid VaccineType vaccineType, BindingResult bindingResult){
        vaccineTypeValidator.validate(vaccineType,bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("vaccineType",vaccineType);
            return "updateVaccineType";
        }
        vaccineTypeService.save(vaccineType);
        return "redirect:/vaccineTypeList";
    }
//    @DeleteMapping(value = "/VaccineTypeList/delete")
//    @ResponseBody
//    public ResponseEntity<String> categoryDelete(@RequestParam(value = "id", required = false) String vaccineTypeId) {
//        VaccineType vaccineType = vaccineTypeService.findById(vaccineTypeId);
//        if (vaccineType == null) {
//            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
//        }
//
//        vaccineType.setStatus(false);
//        vaccineTypeService.save(vaccineType);
//        return new ResponseEntity<>("OK", HttpStatus.OK);
//    }

    @PostMapping("/delete")
    public String deleteVaccineTypes(@RequestParam(value = "vaccineIds", required = false) List<String> vaccineIds) {
        if (vaccineIds != null) {
            for (String id : vaccineIds) {
                Optional<VaccineType> vaccineType = vaccineTypeRepository.findById(id);
                if (vaccineType.isPresent()) {
                    vaccineType.get().setStatus(false);
                    vaccineTypeRepository.save(vaccineType.get());
                }
            }
        }
        return "redirect:/vaccineTypeList";
    }
}



