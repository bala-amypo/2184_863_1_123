package com.example.demo.service.Impl;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<EmployeeSkill> getSkillsByEmployeeId(Long employeeId) {
        // Uses repository method findByEmployeeIdAndActiveTrue
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkillId(Long skillId) {
        // Uses repository method findBySkillIdAndActiveTrue
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }
}
