package com.example.demo.service.Impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(EmployeeSkillRepository employeeSkillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<Employee> findEmployeesByAllSkills(List<String> skills) {
        return employeeSkillRepository.findEmployeesByAllSkillNames(skills); // single param
    }
}
