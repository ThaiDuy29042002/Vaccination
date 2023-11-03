package com.example.vaccination.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    public String getGenderAsString() {
        return gender ? "female" : "male";
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", indentifyCard='" + indentifyCard + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
