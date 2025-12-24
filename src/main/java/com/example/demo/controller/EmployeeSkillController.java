package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService employeeSkillService;

    public EmployeeSkillController(EmployeeSkillService employeeSkillService) {
        this.employeeSkillService = employeeSkillService;
    }

    // Get skills by employee id
    @GetMapping("/employee/{employeeId}")
    public List<EmployeeSkill> getSkillsByEmployee(@PathVariable Long employeeId) {
        return employeeSkillService.getSkillsByEmployeeId(employeeId);
    }

    // Get employees by skill id
    @GetMapping("/skill/{skillId}")
    public List<EmployeeSkill> getEmployeesBySkill(@PathVariable Long skillId) {
        return employeeSkillService.getEmployeesBySkillId(skillId);
    }
}
