package com.example.mealplanner.network;

import com.example.mealplanner.network.categories.Category;
import com.example.mealplanner.network.randommeal.RandomMeal;

import java.util.List;

public interface RandomMealNetworkCallback {
    public void onSuccessResult(List<RandomMeal> categories);

    public void onFailureResult(String message);
}
