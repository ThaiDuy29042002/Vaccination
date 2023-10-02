package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine_types")
public class VaccineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_type_id", length = 36)
    private int vaccineTypeID;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "vaccine_type_name", length = 50)
    @NotNull
    private String vaccineTypeName;

    @Column(name = "status")
    @NotNull
    private boolean status;

    @OneToMany(mappedBy = "vaccineType", cascade = CascadeType.ALL)
    private List<Vaccine> vaccineList;
}
