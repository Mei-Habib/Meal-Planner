package com.example.mealplanner.model;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.countries.Country;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.ingredients.Ingredient;
import com.example.mealplanner.model.randommeal.RandomMeal;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.NetworkCallback;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.List;

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
    public void getRecipes(NetworkCallback<List<Recipe>> networkCallback) {
        remoteDataSource.getRecipes(networkCallback);
    }

    public void getCategories(NetworkCallback<List<Category>> networkCallback) {
        remoteDataSource.getCategories(networkCallback);
    }

    public void getCountries(NetworkCallback<List<Country>> networkCallback) {
        remoteDataSource.getCountries(networkCallback);
    }

    public void getIngredients(NetworkCallback<List<Ingredient>> networkCallback) {
        remoteDataSource.getIngredients(networkCallback);
    }

    public void getRandomMeal(NetworkCallback<List<RandomMeal>> networkCallback) {
        remoteDataSource.getRandomMeal(networkCallback);
    }


}
