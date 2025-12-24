package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Arrays;

@Service
@Transactional
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    private static final List<String> VALID_PROFICIENCIES = Arrays.asList("Beginner", "Intermediate", "Advanced", "Expert");

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        validateEmployeeSkill(mapping);
        mapping.setActive(true);
        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeSkill not found"));
        validateEmployeeSkill(mapping);
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());
        existing.setEmployee(mapping.getEmployee());
        existing.setSkill(mapping.getSkill());
        return employeeSkillRepository.save(existing);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeSkill not found"));
        existing.setActive(false);
        employeeSkillRepository.save(existing);
    }

    private void validateEmployeeSkill(EmployeeSkill mapping) {
        if (!VALID_PROFICIENCIES.contains(mapping.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }
        if (mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years");
        }
        Employee emp = mapping.getEmployee();
        if (emp == null || !emp.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }
        Skill skill = mapping.getSkill();
        if (skill == null || !skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }
    }
}
