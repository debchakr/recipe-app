package com.example.recipeApp.service;

import com.example.recipeApp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceTest {

    private RecipeService recipeService;

    @BeforeEach
    void setup() {
        recipeService = new RecipeService();

        // Inject test data directly, bypassing JSON load
        List<Recipe> testRecipes = Arrays.asList(
                new Recipe(1, "Tomato Pasta", Arrays.asList("tomato", "pasta")),
                new Recipe(2, "Test Salad", Arrays.asList("lettuce", "chicken"))
        );

        recipeService.setRecipes(testRecipes);
    }

    @Test
    void testGetAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        assertNotNull(recipes);
        assertEquals(2, recipes.size());
    }

    @Test
    void testFindRecipesByIngredients() {
        // Search matching the placeholder recipe name
        List<Recipe> results = recipeService.findRecipesByIngredients(Arrays.asList("tomato", "pasta"));
        assertFalse(results.isEmpty());
        assertEquals("Tomato Pasta", results.get(0).getName());
    }
}
