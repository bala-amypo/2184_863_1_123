package com.example.demo.controller;

import com.example.demo.dto.EmployeeSearchRequest;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeSearchService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class EmployeeSearchController {

    private final EmployeeSearchService employeeSearchService;

    public EmployeeSearchController(EmployeeSearchService employeeSearchService) {
        this.employeeSearchService = employeeSearchService;
    }

    // Search employees
    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestBody EmployeeSearchRequest request) {

        return ResponseEntity.ok(
                employeeSearchService.search(request)
        );
    }
}
