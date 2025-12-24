package com.example.demo.util;

import java.util.Arrays;
import java.util.List;

public class ProficiencyValidator {

    private static final List<String> ALLOWED_PROFICIENCIES =
            Arrays.asList("Beginner", "Intermediate", "Advanced", "Expert");

    public static void validate(String proficiency) {
        if (proficiency == null || !ALLOWED_PROFICIENCIES.contains(proficiency)) {
            throw new IllegalArgumentException("Invalid proficiency");
        }
    }
}
