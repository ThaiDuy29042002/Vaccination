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
@Table(name = "injection_result")
public class InjectionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "injection_result_id", length = 36)
    private String injectionResultID;

    @Column(name = "injection_date")
    private Date injectionDate;

    @Column(name = "injection_place")
    private String injectionPlace;

    @Column(name = "next_injection_date")
    private Date nextInjectionDate;

    @Column(name = "number_of_injection", length = 100)
    private String numberOfInjection;

    @Column(name = "prevention", length =  100)
    private String prevention;

}
