package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    void deactivateEmployee(Long id);

    // For search query
    List<Employee> searchEmployees(List<String> skills, Long userId);
}
