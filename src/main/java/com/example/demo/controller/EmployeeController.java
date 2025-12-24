package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final List<Map<String, Object>> employees = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody Map<String, Object> employee) {
        employees.add(employee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getEmployees() {
        return ResponseEntity.ok(employees);
    }
}
