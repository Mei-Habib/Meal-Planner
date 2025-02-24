package com.example.mealplanner.fragments.explore.view;

import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.countries.Country;
import com.example.mealplanner.model.ingredients.Ingredient;

import java.util.List;

public interface ExploreView {

    void showCategories(List<Category> categories);
    void showCountries(List<Country> countries);
    void showIngredients(List<Ingredient> ingredients);


    void showError(String message);
}
