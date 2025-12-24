package com.example.demo.service;

import com.example.demo.model.Skill;
import java.util.List;

public interface SkillService {
    List<Skill> getAllActiveSkills();
    void deactivateSkill(Long id);
}
