package com.example.vaccine.repository;

import com.example.vaccine.models.entity.InjectionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InjectionResultRepository extends JpaRepository<InjectionResult, Integer> {
}
