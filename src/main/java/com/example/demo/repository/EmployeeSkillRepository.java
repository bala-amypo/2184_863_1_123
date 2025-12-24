package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);

    List<EmployeeSkill> findByActiveTrue();
    
    @Query("SELECT DISTINCT es.employee FROM EmployeeSkill es WHERE es.skill.name IN :skillNames AND es.active = true AND es.employee.active = true GROUP BY es.employee HAVING COUNT(DISTINCT es.skill.name) = :skillCount")
    List<Employee> findEmployeesByAllSkillNames(@Param("skillNames") List<String> skillNames, @Param("skillCount") Long skillCount);
}
