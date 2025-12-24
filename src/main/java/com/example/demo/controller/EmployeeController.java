package com.example.demo.controller;

import com.example.demo.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employees);
    }
}
