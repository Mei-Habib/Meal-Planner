package com.example.mealplanner.network;

import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.randommeal.RandomMealResponse;
import com.example.mealplanner.model.recipes.RecipeResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("api/json/v1/1/categories.php")
    Call<CategoryResponse> getCategories();

    @GET("api/json/v1/1/random.php")
    Call<RandomMealResponse> getRandomMeal();

    @GET("api/json/v1/1/list.php?a=list")
    Call<CountryResponse> getCountries();

    @GET("api/json/v1/1/list.php?i=list")
    Call<IngredientResponse> getIngredients();

    @GET("api/json/v1/1/search.php?f=k")
    Call<RecipeResponse> getRecipes();

}


