package com.example.vaccine.repository;

import com.example.vaccine.models.entity.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineTypeRepository extends JpaRepository<VaccineType, Integer> {
}
