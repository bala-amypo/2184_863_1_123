package com.example.demo.controller;

import com.example.demo.model.Employee; 
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee){
        // Example: returns the registered employee
        Employee saved = authService.register(employee);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Employee employee){
        // Example: returns JWT string
        String token = authService.login(employee.getEmail(), employee.getJobTitle());
        return ResponseEntity.ok(token);
    }
}
