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

    // Custom query to find employees who have all the skills in the list
    @Query("SELECT e FROM Employee e JOIN EmployeeSkill es ON e.id = es.employee.id " +
           "WHERE es.skill.name IN :skillNames " +
           "GROUP BY e.id " +
           "HAVING COUNT(DISTINCT es.skill.name) = :count")
    List<Employee> findEmployeesByAllSkillNames(@Param("skillNames") List<String> skillNames,
                                                @Param("count") Long count);
}
