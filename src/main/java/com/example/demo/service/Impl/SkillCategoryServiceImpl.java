package com.example.demo.service.Impl;

import com.example.demo.model.SearchQueryRecord;
import com.example.demo.model.Employee;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.SearchQueryService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository searchQueryRecordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository searchQueryRecordRepository,
                                  EmployeeSkillRepository employeeSkillRepository){
        this.searchQueryRecordRepository = searchQueryRecordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord query){
        return searchQueryRecordRepository.save(query);
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId){
        if(skills == null || skills.isEmpty()){
            throw new IllegalArgumentException("Skills list must not be empty");
        }

        List<Employee> matchedEmployees = employeeSkillRepository
                .findEmployeesByAllSkillNames(skills, userId);

        // Save search record
        SearchQueryRecord record = new SearchQueryRecord();
        record.setSkillsRequested(String.join(",", skills));
        record.setResultsCount(matchedEmployees.size());
        record.setSearcherId(userId);
        saveQuery(record);

        return matchedEmployees;
    }

    @Override
    public SearchQueryRecord getQueryById(Long id){
        return searchQueryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SearchQueryRecord not found"));
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId){
        return searchQueryRecordRepository.findBySearcherId(userId);
    }
}
