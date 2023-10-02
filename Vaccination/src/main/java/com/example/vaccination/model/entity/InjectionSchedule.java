package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "injection_schedule")
public class InjectionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "injection_schedule_id", length = 36)
    private int injectionScheduletID;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "place", length = 255)
    private String place;

    @Column(name = "start_date")
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
}
