package com.example.demo.repository;

import com.example.demo.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {

    boolean existsByCategoryName(String categoryName);

    Optional<SkillCategory> findByCategoryName(String categoryName);
}
