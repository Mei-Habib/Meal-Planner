package com.example.mealplanner.fragments.favorite.view;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public interface FavoriteView {
    void showFavoriteRecipes(List<Recipe> favoriteRecipes);

    void showError(String message);
}
