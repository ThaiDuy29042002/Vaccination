package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "ADDRESS")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_BIRTH")
    private Date dob;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "IDENTITY_CARD")
    private String identityCard;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "USERNAME")
    private String userName;

    @OneToMany(mappedBy = "customer_i", cascade = CascadeType.ALL)
    private List<InjectionResult> injectionResultList;

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
