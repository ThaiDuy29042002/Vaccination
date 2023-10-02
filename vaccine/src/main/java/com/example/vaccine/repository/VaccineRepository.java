package com.example.vaccine.repository;

import com.example.vaccine.models.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
}
