package com.example.vaccination.repository;

import com.example.vaccination.model.entity.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineTypeRepository extends JpaRepository<VaccineType ,String > {

}
