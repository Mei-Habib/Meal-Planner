package com.example.mealplanner.fragments.home.view;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public interface HomeView {
    void showRecipes(List<Recipe> recipes);

    void showRandomRecipe(List<Recipe> randomRecipe);

    void showRecipesByCategory(List<Recipe> recipes);

    void showError(String message);
}
