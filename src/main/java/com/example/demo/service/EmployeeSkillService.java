package com.example.demo.service;

import java.util.List;
import com.example.demo.model.EmployeeSkill;

public interface EmployeeSkillService {

    EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill);

    List<EmployeeSkill> getSkillsForEmployee(Long employeeId);

    List<EmployeeSkill> getEmployeesBySkill(Long skillId);

    void deactivateEmployeeSkill(Long id);
}
