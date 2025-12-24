package com.example.demo.service;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;

public interface UserService {
    User register(AuthRegisterRequest request);
    AuthResponse login(AuthLoginRequest request);
}
