package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "INJECTION_SCHEDULE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InjectionSchedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INJECTION_SCHEDULE_ID")
    private Integer injectionScheduleId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "PLACE")
    private String place;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VACCINE_ID")
    private Vaccine vaccine_s;
}
