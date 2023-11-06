package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.InjectionResult;
import com.example.vaccination.repository.InjectionResultRepository;
import com.example.vaccination.service.InjectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InjectionResultServiceImpl implements InjectionResultService {

    @Autowired
    private InjectionResultRepository injectionResultRepository;
    @Override
    public List<InjectionResult> findAll() {
        return injectionResultRepository.findAll();
    }
////////////---search
    @Override
    public List<InjectionResult> searchResults(Date startDate, Date endDate, String vaccineTypeName, String prevention) {
        return injectionResultRepository.findResults(startDate, endDate, vaccineTypeName, prevention);
    }

    @Override
    public List<InjectionResult> findResultsByInjectionDate(Date startDate, Date endDate) {
        return injectionResultRepository.findResultsByInjectionDate(startDate,endDate);
    }

    @Override
    public List<InjectionResult> findResultsByVaccineTypeName(String vaccineTypeName) {
        return injectionResultRepository.findResultsByVaccineTypeName(vaccineTypeName);
    }

    @Override
    public List<InjectionResult> findResultsByPrevention(String prevention) {
        return injectionResultRepository.findResultsByPrevention(prevention);
    }

    @Override
    public List<InjectionResult> findResultsByInjectionDateAndPrevention(Date startDate, Date endDate,  String prevention) {
        return injectionResultRepository.findResultsByInjectionDateAndPrevention(startDate, endDate, prevention);
    }

    @Override
    public List<InjectionResult> findResultsByInjectionDateAndVaccineTypeName(Date startDate, Date endDate, String vaccineTypeName) {
        return injectionResultRepository.findResultsByInjectionDateAndVaccineTypeName(startDate, endDate, vaccineTypeName);
    }

    @Override
    public List<InjectionResult> findResultsByVaccineTypeNameAndPrevention(String prevention, String vaccineTypeName) {
        return injectionResultRepository.findResultsByVaccineTypeNameAndPrevention(prevention,vaccineTypeName);
    }
    @Override
    public List<InjectionResult> findResultsByStartDate(Date startDate) {
        return injectionResultRepository.findResultsByStartDate(startDate);
    }

    @Override
    public List<InjectionResult> findResultsByEndDate(Date endDate) {
        return injectionResultRepository.findResultsByEndDate(endDate);
    }

    @Override
    public List<InjectionResult> findResultsByStartDateAndPrevention(Date startDate, String prevention) {
        return injectionResultRepository.findResultsByStartDateAndPrevention(startDate, prevention);
    }

    @Override
    public List<InjectionResult> findResultsByStartDateAndVaccineTypeName(Date startDate, String vaccineTypeName) {
        return injectionResultRepository.findResultsByStartDateAndVaccineTypeName(startDate, vaccineTypeName);
    }

    @Override
    public List<InjectionResult> findResultsByStartDateAndPreventionAndVaccineTypeName(Date startDate, String prevention, String vaccineTypeName) {
        return injectionResultRepository.findResultsByStartDateAndPreventionAndVaccineTypeName(startDate, prevention, vaccineTypeName);
    }

    @Override
    public List<InjectionResult> findResultsByEndDateAndPrevention(Date endDate, String prevention) {
        return injectionResultRepository.findResultsByEndDateAndPrevention(endDate, prevention);
    }

    @Override
    public List<InjectionResult> findResultsByEndDateAndVaccineTypeName(Date endDate, String vaccineTypeName) {
        return injectionResultRepository.findResultsByEndDateAndVaccineTypeName(endDate, vaccineTypeName);
    }

    @Override
    public List<InjectionResult> findResultsByEndDateAndPreventionAndVaccineTypeName(Date endDate, String prevention, String vaccineTypeName) {
        return injectionResultRepository.findResultsByEndDateAndPreventionAndVaccineTypeName(endDate, prevention, vaccineTypeName);
    }

///---------Chart

    @Override
    public List<String> count(String yearSelect) {
        return injectionResultRepository.CountInjectionResult(yearSelect);
    }



}
