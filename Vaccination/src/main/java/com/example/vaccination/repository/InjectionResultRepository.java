package com.example.vaccination.repository;

import com.example.vaccination.model.entity.InjectionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface InjectionResultRepository extends JpaRepository<InjectionResult, Integer> {

    InjectionResult findByInjectionResultID(int id);
}
