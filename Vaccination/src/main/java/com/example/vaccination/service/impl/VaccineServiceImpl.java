package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.Vaccine;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class VaccineServiceImpl implements VaccineService {
    @Autowired
    VaccineRepository vaccineRepository;
    @Override
    public Vaccine save(Vaccine vaccine){
        return vaccineRepository.save(vaccine);
    }
}
