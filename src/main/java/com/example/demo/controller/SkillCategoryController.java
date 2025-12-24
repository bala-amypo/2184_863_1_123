package com.example.demo.controller;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final SkillCategoryService categoryService;

    public SkillCategoryController(SkillCategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<SkillCategory> createCategory(@RequestBody SkillCategory category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillCategory> updateCategory(@PathVariable Long id, @RequestBody SkillCategory category){
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillCategory> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<SkillCategory>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCategory(@PathVariable Long id){
        categoryService.deactivateCategory(id);
        return ResponseEntity.ok().build();
    }
}
