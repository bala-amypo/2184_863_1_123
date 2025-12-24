package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final List<Map<String, Object>> categories = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> addCategory(@RequestBody Map<String, Object> category) {
        categories.add(category);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getCategories() {
        return ResponseEntity.ok(categories);
    }
}
