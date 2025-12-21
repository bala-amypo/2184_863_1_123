package com.example.demo.service.impl;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository repo;
    private final EmployeeRepository employeeRepo;
    private final SkillRepository skillRepo;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository repo,EmployeeRepository employeeRepo,SkillRepository skillRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public List<EmployeeSkill> getAllEmployeeSkills() {
        return repo.findAll();
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        validateMapping(mapping);
        mapping.setActive(true);
        return repo.save(mapping);
    }

    @Override
    public EmployeeSkill getEmployeeSkillById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeSkill id not found"));
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill newMapping) {
        EmployeeSkill existing = getEmployeeSkillById(id);
        validateMapping(newMapping);
        newMapping.setId(existing.getId());
        return repo.save(newMapping);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill existing = getEmployeeSkillById(id);
        existing.setActive(false);
        repo.save(existing);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return repo.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return repo.findBySkillIdAndActiveTrue(skillId);
    }

    private void validateMapping(EmployeeSkill mapping) {
        Employee employee = mapping.getEmployee();
        Skill skill = mapping.getSkill();

        if (mapping.getYearsOfExperience() != null && mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be non-negative");
        }

        if (mapping.getProficiencyLevel() == null ||
                !Set.of("BEGINNER", "INTERMEDIATE", "ADVANCED").contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        if (employee == null || employee.getActive() != null && !employee.getActive()) {
            throw new IllegalArgumentException("Inactive employee");
        }

        if (skill == null || skill.getActive() != null && !skill.getActive()) {
            throw new IllegalArgumentException("Inactive skill");
        }
    }
}
