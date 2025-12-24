package com.example.demo.service;

import com.example.demo.model.Skill;

import java.util.List;

public interface SkillService {
    Skill create(Skill skill);
    List<Skill> getAll();
    Skill getById(Long id);
}
