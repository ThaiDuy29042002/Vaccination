package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccine_type")
public class VaccineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_type_id", length = 36)
    private int vaccineTypeID;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "vaccine_type_name", length = 50)
    private String vaccineTypeName;

    @OneToMany(mappedBy = "vaccineType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vaccine> vaccineList = new ArrayList();
}
