package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final EmployeeRepository employeeRepository;

    public AuthController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee){
        if(employeeRepository.existsByEmail(employee.getEmail())){
            throw new IllegalArgumentException("Email already registered");
        }
        employee.setActive(true);
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee employee){
        Employee emp = employeeRepository.findAll().stream()
                .filter(e -> e.getEmail().equals(employee.getEmail()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Simple authentication: use jobTitle as a placeholder for password
        if(!emp.getJobTitle().equals(employee.getJobTitle())){
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Return a fake JWT token string
        return ResponseEntity.ok("fake-jwt-token-for-" + emp.getId());
    }
}
