package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerID;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "email",length = 100, unique = true)
    @NotNull
    private String email;

    @Column(name = "full_name", length = 100)
    @NotNull
    private String fullName;

    @Column(name = "gender")
    @NotNull
    private boolean gender;

    @Column(name = "indentify_card", length = 12, unique = true)
    @NotNull
    private String indentifyCard;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "phone", length = 20, unique = true)
    @NotNull
    @Pattern(regexp = "^[0-9]+$")
    private String phone;

    @Column(name = "username", unique = true)
    @NotNull
    private String username;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<InjectionResult> injectionResultList;

    // For customer report
    @Transient
    private Long numberOfInject; // Transient field

    public void setNumberOfInject(Long numberOfInject) {
        this.numberOfInject = numberOfInject;
    }
}