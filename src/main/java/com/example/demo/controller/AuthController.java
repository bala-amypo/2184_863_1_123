package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        // Dummy login token
        return ResponseEntity.ok("dummy-token");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        // Dummy registration
        return ResponseEntity.ok("user-registered");
    }
}
