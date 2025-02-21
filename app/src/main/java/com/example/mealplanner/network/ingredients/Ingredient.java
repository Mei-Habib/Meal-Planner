package com.example.mealplanner.network.ingredients;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("strIngredient")
    private String ingredient;

    @SerializedName("idIngredient")
    private String id;

    public String getIngredient() {
        return ingredient;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "Ingredient{" +
                        ",ingredient = '" + ingredient + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}