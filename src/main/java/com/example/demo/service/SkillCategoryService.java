package com.example.demo.service;

import com.example.demo.model.SkillCategory;

import java.util.List;

public interface SkillCategoryService {
    SkillCategory create(SkillCategory category);
    List<SkillCategory> getAll();
    SkillCategory getById(Long id);
}
