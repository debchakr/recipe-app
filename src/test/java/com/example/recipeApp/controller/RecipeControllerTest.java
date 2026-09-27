package com.example.recipeApp.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.example.recipeApp.model.Recipe;
import com.example.recipeApp.payload.IngredientSearchRequest;
import com.example.recipeApp.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    void testGetAllRecipes() {
        List<Recipe> fakeRecipes = Arrays.asList(
                new Recipe(1, "Tomato Pasta", Collections.emptyList()),
                new Recipe(2, "Chicken Salad", Collections.emptyList())
        );
        when(recipeService.getAllRecipes()).thenReturn(fakeRecipes);
        List<Recipe> result = recipeController.getAllRecipes();
        assertEquals(2, result.size());
        verify(recipeService, times(1)).getAllRecipes();
    }

    @Test
    void testSearchRecipes_ValidInput() {
        IngredientSearchRequest request = new IngredientSearchRequest();
        request.setIngredients(Arrays.asList("tomato", "olive oil"));
        List<Recipe> mockResults = Arrays.asList(new Recipe(1, "Tomato Pasta", Arrays.asList("tomato", "olive oil")));
        when(recipeService.findRecipesByIngredients(request.getIngredients())).thenReturn(mockResults);
        ResponseEntity<?> response = recipeController.searchRecipes(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResults, response.getBody());
        verify(recipeService, times(1)).findRecipesByIngredients(request.getIngredients());
    }

    @Test
    void testSearchRecipes_EmptyInput() {
        IngredientSearchRequest request = new IngredientSearchRequest();
        request.setIngredients(Collections.emptyList());
        ResponseEntity<?> response = recipeController.searchRecipes(request);
        assertEquals(400, response.getStatusCodeValue());
        verify(recipeService, times(0)).findRecipesByIngredients(any());
    }
}