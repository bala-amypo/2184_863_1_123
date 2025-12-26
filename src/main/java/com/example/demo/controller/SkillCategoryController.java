package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    public SkillCategoryController(SkillCategoryService skillCategoryService){
        this.skillCategoryService = skillCategoryService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SkillCategory> createCategory(@RequestBody SkillCategory category){
        return ResponseEntity.ok(skillCategoryService.createCategory(category));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillCategory> updateCategory(@PathVariable Long id, @RequestBody SkillCategory category){
        return ResponseEntity.ok(skillCategoryService.updateCategory(id, category));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SkillCategory> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(skillCategoryService.getCategoryById(id));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<SkillCategory>> getAllCategories(){
        return ResponseEntity.ok(skillCategoryService.getAllCategories());
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateCategory(@PathVariable Long id){
        skillCategoryService.deactivateCategory(id);
        return ResponseEntity.ok().build();
    }
}
