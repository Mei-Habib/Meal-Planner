package com.example.mealplanner.network;

import com.example.mealplanner.network.categories.CategoryResponse;
import com.example.mealplanner.network.country.CountryResponse;
import com.example.mealplanner.network.randommeal.RandomMealResponse;
import com.example.mealplanner.network.recipes.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("random.php")
    Call<RandomMealResponse> getRandomMeal();

    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();

    @GET("list.php?i=list")
    Call<CountryResponse> getIngredients();

    @GET("search.php?f=f")
    Call<RecipeResponse> getRecipes();
}


