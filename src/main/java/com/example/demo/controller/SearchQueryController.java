package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.SearchQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    private final SearchQueryService searchQueryService;

    public SearchQueryController(SearchQueryService searchQueryService) {
        this.searchQueryService = searchQueryService;
    }

    /**
     * Search employees by skills.
     * @param skills List of skill names to search.
     * @param userId ID of the user performing the search.
     * @return List of employees matching all the skills.
     */
    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> searchEmployeesBySkills(
            @RequestParam Long userId,
            @RequestBody List<String> skills
    ) {
        if (skills == null || skills.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Employee> employees = searchQueryService.searchEmployeesBySkills(skills, userId);
        return ResponseEntity.ok(employees);
    }

    /**
     * Get past search queries for a user.
     */
    @GetMapping("/queries/{userId}")
    public ResponseEntity<?> getQueriesForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(searchQueryService.getQueriesForUser(userId));
    }

    /**
     * Get a specific search query by its ID.
     */
    @GetMapping("/query/{queryId}")
    public ResponseEntity<?> getQueryById(@PathVariable Long queryId) {
        return ResponseEntity.ok(searchQueryService.getQueryById(queryId));
    }
}
