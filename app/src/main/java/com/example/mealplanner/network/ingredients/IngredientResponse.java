package com.example.mealplanner.network.ingredients;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class IngredientResponse {

    @SerializedName("meals")
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "ingredients = '" + ingredients + '\'' +
                        "}";
    }
}