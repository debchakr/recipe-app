package com.example.recipeApp.payload;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class IngredientSearchRequest {
    @NotEmpty(message = "Ingredients list cannot be empty")
    private List<String> ingredients;
    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
}
