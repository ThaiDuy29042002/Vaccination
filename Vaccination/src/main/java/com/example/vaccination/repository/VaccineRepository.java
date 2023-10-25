package com.example.vaccination.repository;

import com.example.vaccination.model.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, String> {
    Optional<Vaccine> findByVaccineName(String vaccineName);
}
