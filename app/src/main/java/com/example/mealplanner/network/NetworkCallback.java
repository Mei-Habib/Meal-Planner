package com.example.mealplanner.network;

public interface NetworkCallback<T> {
    public void onSuccessResult(T result);

    public void onFailureResult(String message);
}
