package com.example.mealplanner.model;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.Country;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.ingredients.Ingredient;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.randommeal.RandomMeal;
import com.example.mealplanner.model.randommeal.RandomMealResponse;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.model.recipes.RecipeResponse;
import com.example.mealplanner.network.NetworkCallback;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class RecipesRepository {
    private RecipeRemoteDataSource remoteDataSource;
    private RecipesLocalDataSource localDataSource;
    private static RecipesRepository productsRepository = null;

    private RecipesRepository(RecipeRemoteDataSource remoteDataSource, RecipesLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }


    public static RecipesRepository getInstance(RecipeRemoteDataSource remoteDataSource, RecipesLocalDataSource localDataSource) {
        if (productsRepository == null) {
            productsRepository = new RecipesRepository(remoteDataSource, localDataSource);
        }

        return productsRepository;
    }

    // local
    public void addProduct(Recipe recipe) {
        localDataSource.addProduct(recipe);
    }

    public void removeProduct(Recipe recipe) {
        localDataSource.removeProduct(recipe);
    }

    public LiveData<List<Recipe>> getStoredProducts() {
        return localDataSource.getStoredProducts();
    }

    // remote
    public Single<RecipeResponse> getRecipes() {
        return remoteDataSource.getRecipes();
    }

    public Single<CategoryResponse> getCategories() {
        return remoteDataSource.getCategories();
    }

    public Single<CountryResponse> getCountries() {
        return remoteDataSource.getCountries();
    }

    public Single<IngredientResponse> getIngredients() {
        return remoteDataSource.getIngredients();
    }

    public Single<RandomMealResponse> getRandomMeal() {
        return remoteDataSource.getRandomMeal();
    }


}
