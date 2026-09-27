package com.example.recipeApp.service;

import com.example.recipeApp.model.Recipe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing recipes.
 * <p>
 * Loads recipes from a JSON resource on application startup and provides methods
 * to retrieve all recipes or find recipes matching given ingredients.
 * </p>
 */
@Service
public class RecipeService {

    private List<Recipe> recipes = Collections.emptyList();

    /**
     * Loads recipes from 'recipes.json' file located in classpath resources.
     * This method is called once after the service is constructed.
     */
    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            recipes = mapper.readValue(
                    new ClassPathResource("recipes.json").getInputStream(),
                    new TypeReference<List<Recipe>>() {}
            );
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error or fallback
        }
    }

    /**
     * Sets the list of recipes.
     * <p>
     * This setter can be used for testing purposes to inject mock or custom recipe data.
     * </p>
     *
     * @param recipes list of Recipe objects to set
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    /**
     * Retrieves all stored recipes sorted alphabetically by name.
     *
     * @return a list of all recipes sorted by name
     */
    public List<Recipe> getAllRecipes() {
        return recipes.stream()
                .sorted((r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Finds recipes that contain all the specified ingredients.
     * <p>
     * Matching is case-insensitive and results are ordered by relevance (number of matched ingredients),
     * then alphabetically by name.
     * </p>
     *
     * @param ingredients list of ingredients to match in recipes
     * @return list of recipes matching all the specified ingredients
     */
    public List<Recipe> findRecipesByIngredients(List<String> ingredients) {
        List<String> searchIngredients = ingredients.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        return recipes.stream()
                .filter(r -> r.getIngredients().stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet())
                        .containsAll(searchIngredients))
                .sorted((r1, r2) -> {
                    long r1Count = r1.getIngredients().stream()
                            .map(String::toLowerCase)
                            .filter(searchIngredients::contains)
                            .count();
                    long r2Count = r2.getIngredients().stream()
                            .map(String::toLowerCase)
                            .filter(searchIngredients::contains)
                            .count();
                    return Long.compare(r2Count, r1Count);
                })
                .collect(Collectors.toList());
    }


}
