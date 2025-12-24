package com.example.demo.repository;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);

    @Query("""
        SELECT DISTINCT es.employee 
        FROM EmployeeSkill es 
        JOIN es.skill s 
        WHERE s.name IN :skills AND es.employee.id != :userId AND es.active = true
        """)
    List<Employee> findEmployeesByAllSkillNames(@Param("skills") List<String> skills, @Param("userId") Long userId);
}
