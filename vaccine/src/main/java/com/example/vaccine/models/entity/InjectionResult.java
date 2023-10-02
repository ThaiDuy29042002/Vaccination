package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "INJECTION_RESULT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InjectionResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INJECTION_RESULT_ID")
    private Integer injectionResultId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer_i;

    @Column(name = "INJECTION_DATE")
    private Date injectionDate;

    @Column(name = "INJECTION_PLACE")
    private String  injectionPlace;

    @Column(name = "NEXT_INJECTION_DATE")
    private Date nextInjectionDate;

    @Column(name = "NUMBER_OF_INJECTION")
    private String numberOfInjection;

    @Column(name = "PREVENTION")
    private String prevention;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VACCINE_ID")
    private Vaccine vaccine_r;


}
