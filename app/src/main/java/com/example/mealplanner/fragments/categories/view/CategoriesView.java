package com.example.mealplanner.fragments.categories.view;

import com.example.mealplanner.model.categories.Category;

import java.util.List;

public interface CategoriesView {
    void showCategories(List<Category> categories);

    void showError(String message);
}
