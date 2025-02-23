package com.example.mealplanner.model.ingredients;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class IngredientResponse {

    @SerializedName("meals")
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

}