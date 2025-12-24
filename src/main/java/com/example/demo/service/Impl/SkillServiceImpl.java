package com.example.demo.service.Impl;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAllActiveSkills() {
        return skillRepository.findByActiveTrue();
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow();
        skill.setActive(false);
        skillRepository.save(skill);
    }
}
