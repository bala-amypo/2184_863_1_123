package com.example.demo.controller;

import com.example.demo.model.SkillCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final List<SkillCategory> categories = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<SkillCategory> addCategory(@RequestBody SkillCategory category) {
        categories.add(category);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/")
    public ResponseEntity<List<SkillCategory>> getCategories() {
        return ResponseEntity.ok(categories);
    }
}
