package com.example.mealplanner.fragments.recipedetails.view;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public interface RecipeDetailsView {
    void showMessage(String message);

    void showError(String message);

    void toggleFavBtn(Boolean isExist);

    void toggleCalendarBtn(Boolean isExist);

    void onRemovedFromFavSuccess(String message);

    void onAddedToFavSuccess(String message);
    void onAddedToPlanSuccess(String message);
}
