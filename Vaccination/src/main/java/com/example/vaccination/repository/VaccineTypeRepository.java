package com.example.vaccination.repository;

import com.example.vaccination.model.entity.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccineTypeRepository extends JpaRepository<VaccineType, Integer> {
    List<VaccineType> findAll();
    VaccineType findByVaccineTypeID(int id);
}
