package com.example.mealplanner.network;

import com.example.mealplanner.network.categories.CategoryResponse;
import com.example.mealplanner.network.randommeal.RandomMealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("random.php")
    Call<RandomMealResponse> getRandomMeal();
}


