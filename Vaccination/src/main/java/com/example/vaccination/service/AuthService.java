package com.example.vaccination.service;


import com.example.vaccination.model.request.AuthRequest;
import com.example.vaccination.model.request.LoginRequest;
import com.example.vaccination.model.request.RegisterRequest;
import com.example.vaccination.model.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(AuthRequest request);

}
