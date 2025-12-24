package com.example.demo.controller;

import com.example.demo.model.SearchQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search-queries")
public class SearchQueryController {

    private final List<SearchQuery> queries = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<SearchQuery> addQuery(@RequestBody SearchQuery query) {
        queries.add(query);
        return ResponseEntity.ok(query);
    }

    @GetMapping("/")
    public ResponseEntity<List<SearchQuery>> getQueries() {
        return ResponseEntity.ok(queries);
    }
}
