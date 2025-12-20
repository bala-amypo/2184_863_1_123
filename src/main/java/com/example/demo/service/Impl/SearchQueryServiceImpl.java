package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository searchQueryRecordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository searchQueryRecordRepository,
                                  EmployeeSkillRepository employeeSkillRepository) {
        this.searchQueryRecordRepository = searchQueryRecordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord query) {
        return searchQueryRecordRepository.save(query);
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("Skills list must not be empty");
        }

        // Fetch all active EmployeeSkill mappings
        List<EmployeeSkill> activeMappings = employeeSkillRepository.findByActiveTrue();

        // Map to track which employee has which skills
        Map<Long, Set<String>> employeeSkills = new HashMap<>();
        Map<Long, Employee> employeeMap = new HashMap<>();

        for (EmployeeSkill es : activeMappings) {
            Employee emp = es.getEmployee();
            if (emp.getId().equals(userId)) continue; // skip searching user
            employeeSkills.computeIfAbsent(emp.getId(), k -> new HashSet<>())
                          .add(es.getSkill().getName());
            employeeMap.put(emp.getId(), emp);
        }

        // Keep only employees who have all requested skills
        List<Employee> matchedEmployees = employeeSkills.entrySet().stream()
                .filter(e -> e.getValue().containsAll(skills))
                .map(e -> employeeMap.get(e.getKey()))
                .toList();

        // Save the search query record
        SearchQueryRecord record = new SearchQueryRecord();
        record.setSkillsRequested(String.join(",", skills));
        record.setResultsCount(matchedEmployees.size());
        record.setSearcherId(userId);

        saveQuery(record);

        return matchedEmployees;
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return searchQueryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SearchQueryRecord not found"));
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return searchQueryRecordRepository.findBySearcherId(userId);
    }
}
