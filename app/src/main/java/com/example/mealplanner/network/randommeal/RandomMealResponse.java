package com.example.mealplanner.network.randommeal;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RandomMealResponse {

    @SerializedName("meals")
    private List<RandomMeal> randomMeal;

    public List<RandomMeal> getMeal() {
        return randomMeal;
    }

}