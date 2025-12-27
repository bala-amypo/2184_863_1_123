package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill createSkill(Skill skill) {
        validateSkill(skill);
        skill.setActive(true);
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
    }

    @Override
    public Skill updateSkill(Long id, Skill newSkill) {
        Skill existing = getSkillById(id);
        validateSkill(newSkill);
        existing.setName(newSkill.getName());
        existing.setDescription(newSkill.getDescription());
        return skillRepository.save(existing);
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill existing = getSkillById(id);
        existing.setActive(false);
        skillRepository.save(existing);
    }
    
    private void validateSkill(Skill skill) {
        if (skill.getName() == null || skill.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name must not be empty");
        }
    }
}