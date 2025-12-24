package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

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
