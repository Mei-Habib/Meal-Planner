package com.example.mealplanner.network;

import com.example.mealplanner.network.categories.Category;

import java.util.List;

public interface NetworkCallback<T> {
    public void onSuccessResult(T result);

    public void onFailureResult(String message);
}
