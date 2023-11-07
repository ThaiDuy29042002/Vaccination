package com.example.vaccination.service;

import com.example.vaccination.model.entity.Customer;
import com.example.vaccination.model.entity.InjectionResult;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface InjectionResultService {

    //---List all
    List<InjectionResult> findAll();

    ///---Find by all---------------------------------------------------------------------------------
    List<InjectionResult> searchResults(Date startDate, Date endDate, String vaccineTypeName, String prevention);

//    ///---Tìm kiếm bằng startDate và endDate-------------------------------------------------------
//    List<InjectionResult> findResultsByInjectionDate(Date startDate, Date endDate);
//
//    ///---Find by Vaccine Type---------------------------------------------------------------------
//    List<InjectionResult> findResultsByVaccineTypeName(String vaccineTypeName);
//
//    ///---find by prevention ------------------------------------------------------------------------
//    List<InjectionResult> findResultsByPrevention(String prevention);
//
//    //---From To and privention--------------------------------------------
//    List<InjectionResult> findResultsByInjectionDateAndPrevention(Date startDate, Date endDate,  String prevention);
//
//    ///---From To and Vaccine Type-------------------------------------------
//    List<InjectionResult> findResultsByInjectionDateAndVaccineTypeName(Date startDate, Date endDate,  String vaccineTypeName);
//
//    ///---Find by From date------------------------------------------------------------------------
//    List<InjectionResult> findResultsByStartDate(Date startDate);
//
//    ///---Find by end date-------------------------------------------------------------------------
//    List<InjectionResult> findResultsByEndDate(Date endDate);
//
//    ///---Find From and Privention
//    List<InjectionResult> findResultsByStartDateAndPrevention(Date startDate, String prevention);
//
//    ///--Find From and VCTName
//    List<InjectionResult> findResultsByStartDateAndVaccineTypeName(Date startDate, String vaccineTypeName);
//
//    ///--Find From and Prevention and VaccineTName
//    List<InjectionResult> findResultsByStartDateAndPreventionAndVaccineTypeName(Date startDate, String prevention, String vaccineTypeName);
//
//    //--Find To and Prevention
//    List<InjectionResult> findResultsByEndDateAndPrevention(Date endDate, String prevention);
//
//    //--Find To and VaccineTName
//    List<InjectionResult> findResultsByEndDateAndVaccineTypeName(Date endDate, String vaccineTypeName);
//
//    //--Find To and Prevention and VCTName
//    List<InjectionResult> findResultsByEndDateAndPreventionAndVaccineTypeName(Date endDate, String prevention, String vaccineTypeName);
//
//    List<InjectionResult> findResultsByVaccineTypeNameAndPrevention(String prevention, String vaccineTypeName);

    List<String> count(String yearSelect);

}
