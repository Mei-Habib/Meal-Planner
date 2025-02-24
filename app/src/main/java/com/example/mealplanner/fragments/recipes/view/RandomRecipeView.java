package com.example.mealplanner.fragments.recipes.view;

import com.example.mealplanner.model.randommeal.RandomRecipe;
import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public interface RandomRecipeView {
    void showRandomRecipe(List<Recipe> randomRecipe);

    void showError(String message);
}
