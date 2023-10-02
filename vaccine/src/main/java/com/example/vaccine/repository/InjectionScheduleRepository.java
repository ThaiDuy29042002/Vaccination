package com.example.vaccine.repository;

import com.example.vaccine.models.entity.InjectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InjectionScheduleRepository extends JpaRepository<InjectionSchedule, Integer> {
}
