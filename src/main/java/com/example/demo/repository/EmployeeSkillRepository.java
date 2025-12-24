package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    @Query("""
        SELECT e.employee
        FROM EmployeeSkill e
        WHERE e.skill.name IN :skills
        AND e.employee.id <> :userId
        GROUP BY e.employee
        HAVING COUNT(DISTINCT e.skill.name) = :#{#skills.size()}
    """)
    List<Employee> findEmployeesByAllSkillNames(@Param("skills") List<String> skills, @Param("userId") Long userId);

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);
    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
}
