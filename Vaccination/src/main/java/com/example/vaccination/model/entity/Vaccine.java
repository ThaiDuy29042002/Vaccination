package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "vaccines")
public class Vaccine {
    @Id
    @Column(name = "vaccine_id", length = 36)
    @Pattern(regexp = "^[0-9]+$")
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

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "vaccine_name", length = 100)
    @NotNull
    private String vaccineName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_type_id")
    private VaccineType vaccineType;

    @OneToMany(mappedBy = "vaccine_r", cascade = CascadeType.ALL)
    private List<InjectionResult> injectionResults;

    @OneToMany(mappedBy = "vaccine_s", cascade = CascadeType.ALL)
    private List<InjectionSchedule> injectionSchedules;

    @Column(name = "status")
    @NotNull
    private boolean status;
}
