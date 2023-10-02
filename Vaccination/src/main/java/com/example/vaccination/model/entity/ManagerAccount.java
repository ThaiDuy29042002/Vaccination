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
@Table(name = "manager_accounts")
public class ManagerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accId;

    @Column(name = "username", length = 150, unique = true)

    private String username;

    @Column(name = "password", length = 150)
    private String password;
}