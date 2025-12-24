package com.example.demo.service.impl;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository repo;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeSkill add(EmployeeSkill employeeSkill) {
        return repo.save(employeeSkill);
    }

    @Override
    public List<EmployeeSkill> getAll() {
        return repo.findAll();
    }

    @Override
    public List<EmployeeSkill> getByEmployeeId(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
