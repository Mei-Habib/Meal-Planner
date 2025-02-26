package com.example.mealplanner.fragments.planner.view;

import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public interface PlannerView {
    void showPlans(List<Plan> plans);

    void showRecipe(Recipe recipe);
    void showError(String message);
}
