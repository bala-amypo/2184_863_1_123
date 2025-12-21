package com.example.demo.service.Impl;

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
    public List<Skill> getAllSkills(boolean onlyActive) {
        if (onlyActive) {
            return repo.findByActiveTrue();
        }
        return repo.findAll();
    }

    @Override
    public Skill createSkill(Skill skill) {
        if (repo.existsByName(skill.getName())) {
            throw new IllegalArgumentException("Skill name must be unique");
        }
        skill.setActive(true);
        return repo.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill id not found"));
    }

    @Override
    public Skill updateSkill(Long id, Skill newSkill) {
        Skill existing = getSkillById(id);
        newSkill.setId(existing.getId());
        newSkill.setActive(existing.getActive());
        return repo.save(newSkill);
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getSkillById(id); // validate existence
        skill.setActive(false);
        repo.save(skill);
    }
}
