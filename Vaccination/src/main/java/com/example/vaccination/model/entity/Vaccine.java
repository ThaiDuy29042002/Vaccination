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

@Table(name = "vaccines")
public class Vaccine {
    @Id
    @Column(name = "vaccine_id", length = 36)//thieu pattern only number
    private String vaccineID;

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

    @Column(name = "usage", length = 200)
    private String usage;

    @Column(name = "vaccine_name", length = 100)
    @NotNull
    private String vaccineName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacine_type_id")
    @NotNull
    private VaccineType vaccineType;

    @Column(name = "status")
    @NotNull
    private boolean status;
}
