package com.example.recipeApp.controller;


import com.example.recipeApp.model.Recipe;
import com.example.recipeApp.payload.IngredientSearchRequest;
import com.example.recipeApp.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing recipe-related endpoints.
 */
@Tag(name = "Recipe API", description = "Operations related to recipes")
@RestController
@RequestMapping("/recipes")
@Validated
public class RecipeController {

    @Autowired
    private final RecipeService recipeService;

    /**
     * Constructor to initialize RecipeController with a RecipeService.
     *
     * @param recipeService the recipe business service
     */
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Retrieves the list of all available recipes.
     *
     * @return list of recipes
     */
    @Operation(summary = "Retrieve all recipes",
            description = "Returns a list of all recipes sorted by name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of recipes")
            })
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    /**
     * Searches for recipes that include all the specified ingredients.
     *
     * @param request the ingredients to search by
     * @return matching recipes or bad request if input is invalid
     */
    @Operation(summary = "Search recipes by ingredients",
            description = "Returns recipes that contain all ingredients from the request",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful search returning matching recipes"),
                    @ApiResponse(responseCode = "400", description = "Invalid input: ingredients list is empty or missing")
            })
    @PostMapping("/search")
    public ResponseEntity<?> searchRecipes(@Valid @RequestBody IngredientSearchRequest request) {
        List<String> ingredients = request.getIngredients();
        if (ingredients == null || ingredients.isEmpty()) {
            return ResponseEntity.badRequest().body("Ingredients list cannot be empty");
        }
        List<Recipe> matched = recipeService.findRecipesByIngredients(ingredients);
        return ResponseEntity.ok(matched);
    }
}

