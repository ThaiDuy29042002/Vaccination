package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class VaccineTypeServiceImpl implements VaccineTypeService {
    @Autowired
    VaccineTypeRepository vaccineTypeRepository;

    @Override
    public VaccineType save(VaccineType vaccineType){
        return vaccineTypeRepository.save(vaccineType);
    }


    @Override
    public VaccineType findById(int id) {return vaccineTypeRepository.findByVaccineTypeID(id);};
}
