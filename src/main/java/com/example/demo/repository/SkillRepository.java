package com.example.demo.repository;

import com.example.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    boolean existsByName(String name);

    Optional<Skill> findByName(String name);
}
