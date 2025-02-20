package com.example.mealplanner.network.categories;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CategoryResponse {

    @SerializedName("categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

}