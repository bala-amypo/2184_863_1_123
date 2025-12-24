package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.SearchQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    private final SearchQueryService searchQueryService;

    public SearchQueryController(SearchQueryService searchQueryService) {
        this.searchQueryService = searchQueryService;
    }

    // Find employees by list of skill names
    @PostMapping("/employees")
    public List<Employee> findEmployeesBySkills(@RequestBody List<String> skills) {
        return searchQueryService.findEmployeesByAllSkills(skills);
    }
}
