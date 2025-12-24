package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee createEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}
