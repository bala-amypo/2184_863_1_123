package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService employeeSkillService;

    public EmployeeSkillController(EmployeeSkillService employeeSkillService) {
        this.employeeSkillService = employeeSkillService;
    }

    // Add skill to employee
    @PostMapping
    public ResponseEntity<EmployeeSkill> add(
            @RequestBody EmployeeSkill employeeSkill) {

        return ResponseEntity.ok(employeeSkillService.add(employeeSkill));
    }

    // Get all employee skills
    @GetMapping
    public ResponseEntity<List<EmployeeSkill>> getAll() {
        return ResponseEntity.ok(employeeSkillService.getAll());
    }

    // Get skills by employee id
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeSkill>> getByEmployeeId(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                employeeSkillService.getByEmployeeId(employeeId)
        );
    }
}
