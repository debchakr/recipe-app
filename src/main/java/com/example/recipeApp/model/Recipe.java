package com.example.recipeApp.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Represents a recipe, containing an ID, name, and list of ingredients.
 */
@Schema(name = "Recipe", description = "Details about a recipe")
public class Recipe {
    @Schema(description = "Unique identifier of the recipe", example = "1")
    private int id;

    @Schema(description = "Name of the recipe", example = "Tomato Pasta")
    private String name;

    @Schema(description = "List of ingredients needed for the recipe",
            example = "[\"tomato\", \"pasta\", \"olive oil\", \"salt\"]")
    private List<String> ingredients;

    /**
     * No-args constructor needed for JSON serialization/deserialization.
     */
    public Recipe() {
    }


    /**
     * Constructs a Recipe object with all fields.
     *
     * @param id          the unique ID of the recipe
     * @param name        the recipe name
     * @param ingredients the ingredients list
     */
    public Recipe(int id, String name, List<String> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
}
