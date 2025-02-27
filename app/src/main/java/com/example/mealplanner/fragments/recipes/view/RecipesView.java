package com.example.mealplanner.fragments.recipes.view;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public interface RecipesView {
    void showRecipes(List<Recipe> recipes);

    void showError(String message);
}
