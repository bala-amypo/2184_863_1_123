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

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill){
        return ResponseEntity.ok(skillService.createSkill(skill));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill){
        return ResponseEntity.ok(skillService.updateSkill(id, skill));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Skill> getSkill(@PathVariable Long id){
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Skill>> getAllSkills(){
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateSkill(@PathVariable Long id){
        skillService.deactivateSkill(id);
        return ResponseEntity.ok().build();
    }
}
