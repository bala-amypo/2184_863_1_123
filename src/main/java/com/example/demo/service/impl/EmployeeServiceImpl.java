package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SearchQueryService searchQueryService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SearchQueryService searchQueryService) {
        this.employeeRepository = employeeRepository;
        this.searchQueryService = searchQueryService;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setJobTitle(employee.getJobTitle());
        return employeeRepository.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setActive(false);
        employeeRepository.save(existing);
    }

    @Override
    public List<Employee> searchEmployees(List<String> skills, Long userId) {
        // delegate to searchQueryService
        return searchQueryService.searchEmployeesBySkills(skills, userId);
    }
}
