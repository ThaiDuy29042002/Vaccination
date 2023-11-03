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
    //Search timeBegin or timeEnd
    @Query("SELECT v FROM Vaccine v WHERE " + "(v.timeBeginNextInjection = :from) or (v.timeEndNextInjection = :to)")
    List<Vaccine> findAllByTimeBeginNextInjectionOrTimeEndNextInjection(Date from, Date to);

    //Search all by Vaccine Type ID
    @Query("SELECT v FROM Vaccine v JOIN v.vaccineType vt WHERE vt.vaccineTypeID = :vaccineSelection")
    List<Vaccine> findAllByVaccineID(String vaccineSelection);

    //Search all when user input all in search form
    @Query("SELECT v FROM Vaccine v JOIN v.vaccineType vt WHERE "
            + "(v.timeBeginNextInjection = :from) AND (v.timeEndNextInjection = :to)"
            + "AND vt.vaccineTypeName = :vaccineSelection AND v.origin LIKE %:origin%")
    List<Vaccine> findAllByCriteria(@Param("from") Date from,
                                    @Param("to") Date to,
                                    @Param("vaccineSelection") String vaccineSelection,
                                    @Param("origin") String origin);

    //Search all by Origin
    List<Vaccine> findAllByOriginContains(String origin);

    //Find Vaccine's data to generate Chart by Number_of_injection / Time_End
    @Query(value="SELECT MONTHNAME(v.time_end_next_injection), SUM(CAST(v.number_of_injection  AS signed)) AS totalNumberOfInjection FROM vaccine v " +
            "WHERE YEAR(v.time_end_next_injection) = :yearSelect GROUP BY MONTHNAME(v.time_end_next_injection);",
            nativeQuery=true)
    List<String> CountVaccine(String yearSelect);
}