package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "VACCINE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACCINE_ID")
    private Integer vaccineId;

    @Column(name = "CONTRAINDICATION")
    private String contraindication;

    @Column(name = "INDICATION")
    private String indication;

    @Column(name = "NUMBER_OF_INJECTION")
    private Integer numberOfInjection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_BEGIN_NEXT_INJECTION")
    private Date timeBeginNextInjection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_END_NEXT_INJECTION")
    private Date timeEndNextInjection;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "USAGE")
    private String usage;

    @Column(name = "VACCNINE_NAME")
    private String vaccineName;


    @OneToMany(mappedBy = "vaccine_r", cascade = CascadeType.ALL)
    private List<InjectionResult> injectionResultnList;

    @OneToMany(mappedBy = "vaccine_s", cascade = CascadeType.ALL)
    private List<InjectionSchedule> injectionScheduleList;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Vaccine_Type_ID")
    private VaccineType vaccineType;
}
