package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee newEmployee) {
        Employee existing = getEmployeeById(id);
        newEmployee.setId(existing.getId());
        return repo.save(newEmployee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee id not found"));
    }

    @Override
    public List<Employee> getAllEmployees(boolean onlyActive) {
        if (onlyActive) {
            return repo.findByActiveTrue();
        }
        return repo.findAll();
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee existing = getEmployeeById(id);
        existing.setActive(false);
        repo.save(existing);
    }
}
