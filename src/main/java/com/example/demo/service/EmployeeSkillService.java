package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;
import java.util.List;

public interface EmployeeSkillService {
    List<EmployeeSkill> getSkillsByEmployeeId(Long employeeId);
    List<EmployeeSkill> getEmployeesBySkillId(Long skillId);
    void deactivateEmployeeSkill(Long id); // Optional: if you need deactivate functionality
}
