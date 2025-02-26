package com.example.mealplanner.model;

import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.model.recipes.RecipeResponse;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class RecipesRepository {
    private final RecipeRemoteDataSource remoteDataSource;
    private final RecipesLocalDataSource localDataSource;
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
    public Completable insertRecipe(Recipe recipe) {
        return localDataSource.insertRecipe(recipe);
    }

    public Completable deleteRecipe(Recipe recipe) {
        return localDataSource.deleteRecipe(recipe);
    }

    public Observable<List<Recipe>> getStoredRecipes() {
        return localDataSource.getStoredRecipes();
    }

    public Observable<List<Plan>> getPlans(String date) {
        return localDataSource.getPlans(date);
    }

    public Completable insertPlan(Plan plan) {
        return localDataSource.insertPlan(plan);
    }

    public Completable deletePlan(Plan plan) {
        return localDataSource.deletePlan(plan);
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

    public Single<RecipeResponse> getRandomRecipe() {
        return remoteDataSource.getRandomRecipe();
    }

    public Single<RecipeResponse> getRecipesByCategory(String category) {
        return remoteDataSource.getRecipesByCategory(category);
    }

    public Single<RecipeResponse> searchRecipeByName(String name) {
        return remoteDataSource.searchRecipeByName(name);
    }

}
