package com.example.demo.service.Impl;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryServiceImpl(SkillCategoryRepository skillCategoryRepository){
        this.skillCategoryRepository = skillCategoryRepository;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category){
        if(skillCategoryRepository.existsByCategoryName(category.getCategoryName())){
            throw new IllegalArgumentException("Category name must be unique");
        }
        return skillCategoryRepository.save(category);
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category){
        SkillCategory existing = getCategoryById(id);
        existing.setCategoryName(category.getCategoryName());
        return skillCategoryRepository.save(existing);
    }

    @Override
    public SkillCategory getCategoryById(Long id){
        return skillCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SkillCategory not found"));
    }

    @Override
    public List<SkillCategory> getAllCategories(){
        return skillCategoryRepository.findAll();
    }

    @Override
    public void deactivateCategory(Long id){
        SkillCategory existing = getCategoryById(id);
        existing.setActive(false);
        skillCategoryRepository.save(existing);
    }
}
