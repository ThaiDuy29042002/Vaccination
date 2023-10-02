package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", length = 36)
    private int vaccineID;

    @Column(name = "contraindication", length = 200)
    private String contraindication;

    @Column(name = "indication", length = 200)
    private String indication;

    @Column(name = "number_of_injection", length = 10)
    private int numberOfInjection;

    @Column(name = "origin", length = 50)
    private String origin;

    @Column(name = "time_begin_next_injection")
    private Date timeBeginNextInjection;

    @Column(name = "time_end_next_injection")
    private Date timeEndNextInjection;

    @Column(name = "usages", length = 200)
    private String usages;

    @Column(name = "vaccine_name", length = 100)
    private String vaccineName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_type_id")
    private VaccineType vaccineType;

    @OneToMany(mappedBy = "vaccine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InjectionSchedule> injectionScheduleList = new ArrayList();

    @OneToMany(mappedBy = "vaccine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InjectionResult> injectionResultList = new ArrayList();

}
