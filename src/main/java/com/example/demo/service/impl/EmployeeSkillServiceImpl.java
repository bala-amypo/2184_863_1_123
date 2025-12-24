package com.example.demo.service.impl;

import java.util.List;
import java.util.Set;

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

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    private static final Set<String> VALID_PROFICIENCY =
            Set.of("Beginner", "Intermediate", "Advanced");

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill es) {

        Employee emp = employeeRepository.findById(es.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Skill skill = skillRepository.findById(es.getSkill().getId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        if (!emp.getActive()) {
            throw new IllegalArgumentException("inactive employee");
        }
        if (!skill.getActive()) {
            throw new IllegalArgumentException("inactive skill");
        }
        if (es.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be positive");
        }
        if (!VALID_PROFICIENCY.contains(es.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        es.setActive(true);
        return employeeSkillRepository.save(es);
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
        EmployeeSkill es = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mapping not found"));

        es.setActive(false);
        employeeSkillRepository.save(es);
    }
}
