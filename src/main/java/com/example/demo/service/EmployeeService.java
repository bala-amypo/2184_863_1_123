package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
