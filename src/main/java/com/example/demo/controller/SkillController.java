package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    @PostMapping("/")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill){
        return ResponseEntity.ok(skillService.createSkill(skill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill){
        return ResponseEntity.ok(skillService.updateSkill(id, skill));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkill(@PathVariable Long id){
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Skill>> getAllSkills(@RequestParam(required = false) Boolean active){
        boolean onlyActive = active != null && active;
        return ResponseEntity.ok(skillService.getAllSkills(onlyActive));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateSkill(@PathVariable Long id){
        skillService.deactivateSkill(id);
        return ResponseEntity.ok().build();
    }
}
