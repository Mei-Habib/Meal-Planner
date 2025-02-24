package com.example.mealplanner.model.recipes;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RecipeResponse {

    @SerializedName("meals")
    private List<Recipe> recipes;


    public List<Recipe> getRecipes() {
        return recipes;
    }

}