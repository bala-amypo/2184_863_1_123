package com.example.demo.service.Impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        validateMapping(mapping);
        mapping.setActive(true);
        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeSkill not found"));

        validateMapping(mapping);

        existing.setEmployee(mapping.getEmployee());
        existing.setSkill(mapping.getSkill());
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());

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
        EmployeeSkill mapping = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeSkill not found"));
        mapping.setActive(false);
        employeeSkillRepository.save(mapping);
    }

    private void validateMapping(EmployeeSkill mapping) {
        Employee employee = mapping.getEmployee();
        Skill skill = mapping.getSkill();

        if (mapping.getYearsOfExperience() != null && mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years must be non-negative");
        }

        if (mapping.getProficiencyLevel() == null ||
                !List.of("BEGINNER", "INTERMEDIATE", "ADVANCED")
                        .contains(mapping.getProficiencyLevel())) {
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
