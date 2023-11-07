package com.example.vaccination.repository;

import com.example.vaccination.model.entity.InjectionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface InjectionResultRepository extends JpaRepository<InjectionResult, Integer> {

    InjectionResult findByInjectionResultID(int id);
}