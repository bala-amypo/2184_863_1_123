package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    @Override
    public Skill createSkill(Skill skill) {
        skill.setActive(true);
        return repo.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public Skill updateSkill(Long id, Skill newSkill) {
        Skill existing = getSkillById(id);
        existing.setName(newSkill.getName());
        existing.setDescription(newSkill.getDescription());
        return repo.save(existing);
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill existing = getSkillById(id);
        existing.setActive(false);
        repo.save(existing);
    }
}