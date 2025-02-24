package com.example.mealplanner.fragments.recipedetails.view;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public interface RecipeDetailsView {
    void showRecipe(Recipe recipe);

    void showError(String message);
}
