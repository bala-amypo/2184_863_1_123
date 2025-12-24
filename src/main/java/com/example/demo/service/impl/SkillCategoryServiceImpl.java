package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository repo;

    public SkillCategoryServiceImpl(SkillCategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        return repo.save(category);
    }

    @Override
    public SkillCategory getCategoryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("SkillCategory id not found"));
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory newCategory) {
        SkillCategory existing = getCategoryById(id);
        newCategory.setId(existing.getId());
        return repo.save(newCategory);
    }

    @Override
    public void deactivateCategory(Long id) {
        SkillCategory existing = getCategoryById(id);
        existing.setActive(false);
        repo.save(existing);
    }
}