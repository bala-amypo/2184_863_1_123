package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryServiceImpl(SkillCategoryRepository skillCategoryRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return skillCategoryRepository.findAll();
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        validateCategory(category);
        return skillCategoryRepository.save(category);
    }

    @Override
    public SkillCategory getCategoryById(Long id) {
        return skillCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SkillCategory not found"));
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory newCategory) {
        SkillCategory existing = getCategoryById(id);
        validateCategory(newCategory);
        existing.setCategoryName(newCategory.getCategoryName());
        existing.setDescription(newCategory.getDescription());
        return skillCategoryRepository.save(existing);
    }

    @Override
    public void deactivateCategory(Long id) {
        SkillCategory existing = getCategoryById(id);
        existing.setActive(false);
        skillCategoryRepository.save(existing);
    }
    
    private void validateCategory(SkillCategory category) {
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be empty");
        }
    }
}