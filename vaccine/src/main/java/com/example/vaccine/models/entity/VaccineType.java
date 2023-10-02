package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "VACCINE_TYPE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACCINE_TYPE_ID")
    private Integer typeId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VACCINE_TYPE_NAME")
    private String typeName;

    @OneToMany(mappedBy = "vaccineType", cascade = CascadeType.ALL)
    private List<Vaccine> vaccineList;
}
