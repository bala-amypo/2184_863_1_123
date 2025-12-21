package com.example.demo.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository repo;
    private final EmployeeSkillRepository employeeSkillRepo;

    public SearchQueryServiceImpl(SearchQueryRecordRepository repo,
                                  EmployeeSkillRepository employeeSkillRepo) {
        this.repo = repo;
        this.employeeSkillRepo = employeeSkillRepo;
    }

    @Override
    public List<SearchQueryRecord> getAllQueries() {
        return repo.findAll();
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord query) {
        return repo.save(query);
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("SearchQueryRecord id not found"));
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return repo.findBySearcherId(userId);
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("Skills list must not be empty");
        }

        List<EmployeeSkill> activeMappings = employeeSkillRepo.findByActiveTrue();
        Map<Long, Set<String>> employeeSkills = new HashMap<>();
        Map<Long, Employee> employeeMap = new HashMap<>();

        for (EmployeeSkill es : activeMappings) {
            Employee emp = es.getEmployee();
            if (emp == null || emp.getId() == null) continue;
            if (emp.getId().equals(userId)) continue;

            employeeSkills
                    .computeIfAbsent(emp.getId(), k -> new HashSet<>())
                    .add(es.getSkill().getName());

            employeeMap.put(emp.getId(), emp);
        }

        List<Employee> matchedEmployees = employeeSkills.entrySet()
                .stream()
                .filter(e -> e.getValue().containsAll(skills))
                .map(e -> employeeMap.get(e.getKey()))
                .toList();

        // Save search query record
        SearchQueryRecord record = new SearchQueryRecord();
        record.setSkillsRequested(String.join(",", skills));
        record.setResultsCount(matchedEmployees.size());
        record.setSearcherId(userId);
        saveQuery(record);

        return matchedEmployees;
    }
}
