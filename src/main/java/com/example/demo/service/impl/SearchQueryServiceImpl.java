package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository repo;
    private final EmployeeSkillRepository employeeSkillRepo;

    public SearchQueryServiceImpl(SearchQueryRecordRepository repo,EmployeeSkillRepository employeeSkillRepo) {
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

        // Normalize skills - trim and convert to lowercase, remove duplicates
        List<String> normalizedSkills = skills.stream()
            .map(skill -> skill.trim().toLowerCase())
            .distinct()
            .toList();

        List<Employee> results = employeeSkillRepo
            .findEmployeesByAllSkillNames(normalizedSkills, (long) normalizedSkills.size());

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSkillsRequested(String.join(",", normalizedSkills));
        record.setResultsCount(results.size());
        record.setSearcherId(userId);
        saveQuery(record);

        return results;
    }
}