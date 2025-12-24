package com.example.demo.controller;

import com.example.demo.model.Skill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final List<Skill> skills = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill) {
        skills.add(skill);
        return ResponseEntity.ok(skill);
    }

    @GetMapping("/")
    public ResponseEntity<List<Skill>> getSkills() {
        return ResponseEntity.ok(skills);
    }
}
