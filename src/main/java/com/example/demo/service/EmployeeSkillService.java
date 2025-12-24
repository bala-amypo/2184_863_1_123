package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;

import java.util.List;

public interface EmployeeSkillService {
    EmployeeSkill add(EmployeeSkill es);
    List<EmployeeSkill> getAll();
    List<EmployeeSkill> getByEmployeeId(Long employeeId);
}
