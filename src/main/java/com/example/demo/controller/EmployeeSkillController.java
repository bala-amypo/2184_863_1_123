package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final List<Map<String, Object>> skills = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> addSkill(@RequestBody Map<String, Object> skill) {
        skills.add(skill);
        return ResponseEntity.ok(skill);
    }

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getSkills() {
        return ResponseEntity.ok(skills);
    }
}
