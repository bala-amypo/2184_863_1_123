package com.example.demo.repository;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;
import com.example.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    // Corrected query: removed unused parameter userId
    @Query("""
        SELECT DISTINCT es.employee
        FROM EmployeeSkill es
        WHERE es.active = true
          AND es.employee.active = true
          AND es.skill.name IN :skills
        GROUP BY es.employee.id
        HAVING COUNT(DISTINCT es.skill.name) = :#{#skills.size()}
    """)
    List<Employee> findEmployeesByAllSkillNames(@Param("skills") List<String> skills);

    // Optional: find skills by employee
    List<EmployeeSkill> findByEmployeeId(Long employeeId);
}
