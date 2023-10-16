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
            errors.rejectValue("vaccineID", "error.VaccineId","VaccineId already exists");
        }
    }
}
