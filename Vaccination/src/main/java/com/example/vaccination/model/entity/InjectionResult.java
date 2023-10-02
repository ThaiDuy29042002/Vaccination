package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "injection_results")
public class InjectionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "injection_result_id")
    private int injectionResultID;

    @Column(name = "injection_date")
    @NotNull
    private Date injectionDate;

    @Column(name = "injection_place")
    private String injectionPlace;

    @Column(name = "next_injection_date")
    @NotNull
    private Date nextInjectionDate;

    @Column(name = "number_of_injection")
    private int numberOfInjection;

    @Column(name = "prevention", length =  100)
    private String prevention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacine_id")
    @NotNull
    private Vaccine vaccine;
}
