package com.example.mealplanner.fragments.explore.view;

import com.example.mealplanner.model.categories.Category;

import java.util.List;

public interface ExploreView {

    void showCategories(List<Category> categories);

    void showError(String message);
}
