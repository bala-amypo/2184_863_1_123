package com.example.demo.service.Impl;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository){
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping){
        Employee emp = employeeRepository.findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Skill skill = skillRepository.findById(mapping.getSkill().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        if(!emp.getActive() || !skill.getActive()){
            throw new IllegalArgumentException("Employee or Skill must be active");
        }
        if(mapping.getYearsOfExperience() < 0){
            throw new IllegalArgumentException("Years of experience must be non-negative");
        }
        if(mapping.getProficiencyLevel() == null || mapping.getProficiencyLevel().isBlank()){
            throw new IllegalArgumentException("Proficiency level must be specified");
        }
        mapping.setActive(true);
        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping){
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeSkill not found"));
        existing.setEmployee(mapping.getEmployee());
        existing.setSkill(mapping.getSkill());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        return createEmployeeSkill(existing);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId){
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId){
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id){
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeSkill not found"));
        existing.setActive(false);
        employeeSkillRepository.save(existing);
    }
}
