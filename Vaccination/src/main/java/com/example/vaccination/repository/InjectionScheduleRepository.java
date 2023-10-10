package com.example.vaccination.repository;

import com.example.vaccination.model.entity.InjectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectionScheduleRepository extends JpaRepository<InjectionSchedule, Integer> {
}
