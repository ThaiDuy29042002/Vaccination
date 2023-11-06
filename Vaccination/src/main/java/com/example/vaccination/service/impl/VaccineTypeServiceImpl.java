package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.VaccineType;
import com.example.vaccination.repository.VaccineRepository;
import com.example.vaccination.repository.VaccineTypeRepository;
import com.example.vaccination.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineTypeServiceImpl implements VaccineTypeService {

    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;

    @Override
    public List<VaccineType> findAll() {
        return vaccineTypeRepository.findAll();
    }
}
