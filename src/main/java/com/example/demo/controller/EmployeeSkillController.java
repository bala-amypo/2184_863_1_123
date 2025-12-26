package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService employeeSkillService;

    public EmployeeSkillController(EmployeeSkillService employeeSkillService){
        this.employeeSkillService = employeeSkillService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeSkill> createMapping(@RequestBody EmployeeSkill mapping){
        return ResponseEntity.ok(employeeSkillService.createEmployeeSkill(mapping));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeSkill> updateMapping(@PathVariable Long id,@RequestBody EmployeeSkill mapping){
        return ResponseEntity.ok(employeeSkillService.updateEmployeeSkill(id, mapping));
    }

    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<EmployeeSkill>> getSkillsForEmployee(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeSkillService.getSkillsForEmployee(employeeId));
    }

    @GetMapping("/skill/{skillId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<EmployeeSkill>> getEmployeesBySkill(@PathVariable Long skillId){
        return ResponseEntity.ok(employeeSkillService.getEmployeesBySkill(skillId));
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateMapping(@PathVariable Long id){
        employeeSkillService.deactivateEmployeeSkill(id);
        return ResponseEntity.ok().build();
    }
}
