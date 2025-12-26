package com.example.demo.controller;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest request) {
    
        return new AuthResponse();
    }
    
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRegisterRequest request) {
        // Implementation would go here
        return new AuthResponse();
    }
}