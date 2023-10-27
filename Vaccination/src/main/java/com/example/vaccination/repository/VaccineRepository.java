package com.example.vaccination.repository;

import com.example.vaccination.model.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Date;


@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, String> {

    @Query("SELECT v FROM Vaccine v WHERE v.timeBeginNextInjection = ?1")
    public List<Vaccine> search(Date from);

    List<Vaccine> findByTimeBeginNextInjection(Date from);
}