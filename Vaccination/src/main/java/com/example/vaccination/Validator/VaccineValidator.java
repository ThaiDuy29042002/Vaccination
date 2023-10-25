package com.example.vaccination.Validator;

import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.service.impl.VaccineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component

public class VaccineValidator implements Validator {

    @Autowired
    private VaccineServiceImpl service;

    @Override
    public boolean supports(Class<?> clazz) {
        return Vaccine.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Vaccine vaccine = (Vaccine) target;
        if (service.findById(vaccine.getVaccineID())!= null){
            errors.rejectValue("vaccineID", "error.VaccineId","Vaccine Code already exists");
        }
        if (service.findByVaccineName(vaccine.getVaccineName()) != null){
            errors.rejectValue("vaccineName", "error.vaccineName", "Vaccine Name already exists");
        }
        if (!vaccine.getVaccineName().matches("^[a-zA-Z0-9]*$") && !vaccine.getVaccineName().isEmpty()){
            errors.rejectValue("vaccineName", "error.vaccineName","Vaccine Name contains invalid characters");
        }
        if (!vaccine.getVaccineID().matches("^[0-9]+$") && !vaccine.getVaccineID().isEmpty()) {
            errors.rejectValue("vaccineID", "error.VaccineId", "Vaccine Code contains invalid characters");
        }
        if (!vaccine.getNumberOfInjection().matches("^[0-9]+$") && !vaccine.getNumberOfInjection().isEmpty()){
            errors.rejectValue("numberOfInjection", "error.numberOfInjection","Number Of Injection contains invalid characters");
        }
    }
}
