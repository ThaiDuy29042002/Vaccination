package com.example.vaccination.controller;

import com.example.vaccination.Validator.VaccineValidator;
import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class VaccineController {

    @Autowired
    private VaccineRepository repository;

    @Autowired
    private VaccineServiceImpl service;

    @Autowired
    private VaccineValidator vaccineValidator;

    @GetMapping("/productall")
    public List<Vaccine> getAllProduct() {
        return service.getAllProducts();
    }

    @GetMapping(path = "/vaccineList")
    public String findAll(Model model) {
        List<Vaccine> listVaccine = service.getAllProducts();
        model.addAttribute("listVaccine", listVaccine);
        return "vaccineList";
    }

    @GetMapping(path = "/addNew")
    public String showRegisterForm(Model model) {
        model.addAttribute("product", new Vaccine());
        return "addNew";
    }

    @PostMapping(path = "/addNew")
    public String save(Model model, @ModelAttribute("product") @Valid Vaccine vaccine, BindingResult bindingResult) {
        vaccineValidator.validate(vaccine, bindingResult);
        if (bindingResult.hasErrors() || vaccine.getTimeBeginNextInjection().after(vaccine.getTimeEndNextInjection())) {
            model.addAttribute("vaccine", vaccine);
            model.addAttribute("error", "TimeBegin must be less than TimeEnd");
            return "addNew";
        }
        service.addNew(vaccine);
        return "redirect:/vaccineList";
    }


    @GetMapping(path = "/product/edit")
    public String editVaccineForm(@RequestParam("id") String id, Model model) {
        Vaccine exists = service.findById(id);
        model.addAttribute("product", exists);
        return "edit_vaccine";
    }


    @PostMapping(path = "/product/edit")
    public String updateVaccine(@ModelAttribute("product") @Valid Vaccine vaccine, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()|| vaccine.getTimeBeginNextInjection().after(vaccine.getTimeEndNextInjection())) {
            model.addAttribute("error", "TimeBegin must be less than TimeEnd");
            return "edit_vaccine";
        }
        service.addNew(vaccine);
        return "redirect:/vaccineList";
    }

    @GetMapping(path = "/vaccineUpload")
    public String upload(Model model) {
        model.addAttribute("vaccine", new Vaccine());
        return "uploadByExcel";
    }
    @PostMapping("/vaccineUpload")
    public String upload(@ModelAttribute("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            this.service.saveByExcel(file);
            return "redirect:/vaccineList";
        }
        return "uploadByExcel";
    }
//    @PostMapping("/vaccine/upload")
//    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
//        if (Helper.checkExcelFormat(file)) {
//            this.service.saveByExcel(file);
//            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
//    }


//    @DeleteMapping(value = "/vaccine/delete")
//    @ResponseBody
//    public ResponseEntity<String> Delete(@RequestParam(value = "id", required = false) String vaccineID) {
//        Vaccine vaccine = service.findById(vaccineID);
//        vaccine.setStatus(false);
//        service.addNew(vaccine);
//        return new ResponseEntity<>("OK", HttpStatus.OK);
//    }

    @PostMapping(value = "/vaccine/delete")
    public String deleteVaccineTypes(@RequestParam(value = "vaccineIds", required = false) List<String> vaccineIds) {
        if (vaccineIds != null) {
            for (String id : vaccineIds) {
                Optional<Vaccine> vaccineType = repository.findById(id);
                if (vaccineType.isPresent()) {
                    vaccineType.get().setStatus(false);
                    repository.save(vaccineType.get());
                }
            }
        }
        return "redirect:/vaccineList";
    }

}
