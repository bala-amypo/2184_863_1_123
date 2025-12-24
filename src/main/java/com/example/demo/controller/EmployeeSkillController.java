package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService employeeSkillService;

    public EmployeeSkillController(EmployeeSkillService employeeSkillService){
        this.employeeSkillService = employeeSkillService;
    }

    @PostMapping
    public ResponseEntity<EmployeeSkill> createMapping(@RequestBody EmployeeSkill mapping){
        return ResponseEntity.ok(employeeSkillService.createEmployeeSkill(mapping));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSkill> updateMapping(@PathVariable Long id, @RequestBody EmployeeSkill mapping){
        return ResponseEntity.ok(employeeSkillService.updateEmployeeSkill(id, mapping));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeSkill>> getSkillsForEmployee(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeSkillService.getSkillsForEmployee(employeeId));
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<EmployeeSkill>> getEmployeesBySkill(@PathVariable Long skillId){
        return ResponseEntity.ok(employeeSkillService.getEmployeesBySkill(skillId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateMapping(@PathVariable Long id){
        employeeSkillService.deactivateEmployeeSkill(id);
        return ResponseEntity.ok().build();
    }
}
