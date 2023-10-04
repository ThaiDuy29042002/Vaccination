package com.example.vaccination.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String empid;
    private String address;
    private Date dateOfBirth;
    private String email;
    private String employeeName;
    private boolean gender;
    private String password;
    private String phone;
    private String username;


}
