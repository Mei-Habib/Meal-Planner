package com.example.mealplanner.network;

import com.example.mealplanner.network.categories.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("categories")
    Call<CategoryResponse> getCategories();
}


