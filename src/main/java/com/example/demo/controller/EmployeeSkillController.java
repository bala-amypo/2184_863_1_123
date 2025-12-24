package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final List<EmployeeSkill> skills = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<EmployeeSkill> addSkill(@RequestBody EmployeeSkill skill) {
        skills.add(skill);
        return ResponseEntity.ok(skill);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeSkill>> getSkills() {
        return ResponseEntity.ok(skills);
    }
}
