package com.example.mealplanner.network;

import com.example.mealplanner.network.categories.Category;

import java.util.List;

public interface NetworkCallback {
    public void onSuccessResult(List<Category> categories);

    public void onFailureResult(String message);
}
