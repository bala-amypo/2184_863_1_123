package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository recordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository recordRepository,
                                  EmployeeSkillRepository employeeSkillRepository) {
        this.recordRepository = recordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {

        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("Skills must not be empty");
        }

        List<String> normalized = skills.stream()
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .collect(Collectors.toList());

        List<Employee> result =
                employeeSkillRepository.findEmployeesByAllSkillNames(
                        normalized, (long) normalized.size());

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(userId);
        record.setSkillsRequested(String.join(",", normalized));
        record.setResultsCount(result.size());
        recordRepository.save(record);

        return result;
    }

    @Override
    public void saveQuery(SearchQueryRecord record) {
        recordRepository.save(record);
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Query not found"));
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return recordRepository.findBySearcherId(userId);
    }
}
