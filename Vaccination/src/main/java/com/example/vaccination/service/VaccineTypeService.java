package com.example.vaccination.service;

import com.example.vaccination.model.entity.VaccineType;

public interface VaccineTypeService {
    VaccineType save(VaccineType vaccineType);
    VaccineType findById(int id);
}
