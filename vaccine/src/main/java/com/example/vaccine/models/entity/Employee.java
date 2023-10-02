package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    @Column(name = "ADDRESS")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_BIRTH")
    private Date dob;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "WORKING_PLACE")
    private String workingPlace;

    @Override
    public String toString() {
        return "Employee{" +
                "address='" + address + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", userName='" + userName + '\'' +
                ", workingPlace='" + workingPlace + '\'' +
                '}';
    }
}
