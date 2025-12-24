package com.example.demo.controller;

import com.example.demo.dto.EmployeeSearchRequest;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ✅ Create employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    // ✅ Get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // ✅ Get employee by ID
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // ✅ Search employees by skills + userId
    @PostMapping("/search/employees")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestBody EmployeeSearchRequest request) {

        return ResponseEntity.ok(
                employeeService.searchEmployees(request.getSkills(), request.getUserId())
        );
    }
}
