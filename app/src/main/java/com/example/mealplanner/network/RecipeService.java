package com.example.mealplanner.network;

import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.randommeal.RandomMealResponse;
import com.example.mealplanner.model.recipes.RecipeResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("api/json/v1/1/categories.php")
    Single<CategoryResponse> getCategories();

    @GET("api/json/v1/1/random.php")
    Single<RandomMealResponse> getRandomMeal();

    @GET("api/json/v1/1/list.php?a=list")
    Single<CountryResponse> getCountries();

    @GET("api/json/v1/1/list.php?i=list")
    Single<IngredientResponse> getIngredients();

    @GET("api/json/v1/1/search.php?f=k")
    Single<RecipeResponse> getRecipes();

}


