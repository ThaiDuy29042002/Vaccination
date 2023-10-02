package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String vaccineTypeID;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "vaccine_type_name", length = 50)
    private String vaccineTypeName;
}
