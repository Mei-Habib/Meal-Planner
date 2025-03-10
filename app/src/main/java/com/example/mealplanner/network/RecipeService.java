package com.example.mealplanner.network;

import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.recipes.RecipeResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeService {
    @GET("api/json/v1/1/categories.php")
    Single<CategoryResponse> getCategories();

    @GET("api/json/v1/1/random.php")
    Single<RecipeResponse> getRandomRecipe();

    @GET("api/json/v1/1/list.php?a=list")
    Single<CountryResponse> getCountries();

    @GET("api/json/v1/1/list.php?i=list")
    Single<IngredientResponse> getIngredients();

    @GET("api/json/v1/1/search.php?f=k")
    Single<RecipeResponse> getRecipes();

    @GET("api/json/v1/1/filter.php?")
    Single<RecipeResponse> searchRecipesByCategory(@Query("c") String category);

    @GET("api/json/v1/1/filter.php?")
    Single<RecipeResponse> searchRecipesByCountry(@Query("a") String country);

    @GET("api/json/v1/1/filter.php?")
    Single<RecipeResponse> searchRecipesByIngredient(@Query("i") String ingredient);

    @GET("api/json/v1/1/search.php?")
    Single<RecipeResponse> searchRecipeByName(@Query("s") String name);

}


