package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees(boolean onlyActive);

    void deactivateEmployee(Long id);
}
