package com.example.mealplanner.network;

import android.util.Log;

import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.recipes.RecipeResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRemoteDataSource {

    public static final String BASE_URL = "https://www.themealdb.com/";
    public static final String TAG = "RecipeRemoteDataSource";
    private final RecipeService service;

    public RecipeRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(RecipeService.class);
    }

    public Single<CategoryResponse> getCategories() {
        Log.i(TAG, "getCategories: ");
        return service.getCategories();
    }

    public Single<RecipeResponse> getRandomRecipe() {
        Log.i(TAG, "getRandomRecipe: ");
        return service.getRandomRecipe();
    }

    public Single<CountryResponse> getCountries() {
        Log.i(TAG, "getCountries: ");
        return service.getCountries();
    }

    public Single<RecipeResponse> getRecipes() {
        Log.i(TAG, "getRecipes: ");
        return service.getRecipes();
    }

    public Single<IngredientResponse> getIngredients() {
        Log.i(TAG, "getIngredients: ");
        return service.getIngredients();
    }

    public Single<RecipeResponse> searchRecipesByCategory(String category) {
        Log.i(TAG, "getIngredients: ");
        return service.searchRecipesByCategory(category);
    }

    public Single<RecipeResponse> searchRecipesByCountry(String country) {
        Log.i(TAG, "getIngredients: ");
        return service.searchRecipesByCountry(country);
    }

    public Single<RecipeResponse> searchRecipesByIngredient(String ingredient) {
        Log.i(TAG, "getIngredients: ");
        return service.searchRecipesByIngredient(ingredient);
    }

    public Single<RecipeResponse> searchRecipeByName(String name) {
        Log.i(TAG, "searchRecipeByName: ");
        return service.searchRecipeByName(name);
    }
}
