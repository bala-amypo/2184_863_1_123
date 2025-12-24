package com.example.demo.controller;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    public SkillCategoryController(SkillCategoryService skillCategoryService) {
        this.skillCategoryService = skillCategoryService;
    }

    // ✅ Create category
    @PostMapping
    public ResponseEntity<SkillCategory> create(
            @RequestBody SkillCategory category) {

        return ResponseEntity.ok(skillCategoryService.create(category));
    }

    // ✅ Get all categories
    @GetMapping
    public ResponseEntity<List<SkillCategory>> getAll() {
        return ResponseEntity.ok(skillCategoryService.getAll());
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<SkillCategory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(skillCategoryService.getById(id));
    }
}
