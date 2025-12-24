package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface SearchQueryService {
    List<Employee> findEmployeesByAllSkills(List<String> skills);
}
