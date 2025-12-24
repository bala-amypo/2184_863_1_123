package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final List<Map<String, Object>> employeeSkills = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> addSkill(@RequestBody Map<String, Object> skill) {
        employeeSkills.add(skill);
        return ResponseEntity.ok(skill);
    }

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getSkills() {
        return ResponseEntity.ok(employeeSkills);
    }
}
