package com.example.recipeApp.payload;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IngredientSearchRequestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testEmptyIngredientsValidation() {
        IngredientSearchRequest request = new IngredientSearchRequest();
        request.setIngredients(Collections.emptyList());
        Set<ConstraintViolation<IngredientSearchRequest>> violations = validator.validate(request);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("Ingredients list cannot be empty")));
        assertFalse(violations.isEmpty());
    }

}