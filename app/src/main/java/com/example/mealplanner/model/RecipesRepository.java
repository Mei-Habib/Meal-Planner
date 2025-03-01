package com.example.mealplanner.model;

import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.model.recipes.RecipeResponse;
import com.example.mealplanner.network.FirestoreDataSource;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class RecipesRepository {
    private final RecipeRemoteDataSource remoteDataSource;
    private final RecipesLocalDataSource localDataSource;
    private final FirestoreDataSource firestoreDataSource;
    private static RecipesRepository productsRepository = null;

    private RecipesRepository(RecipeRemoteDataSource remoteDataSource, RecipesLocalDataSource localDataSource, FirestoreDataSource firestoreDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.firestoreDataSource = firestoreDataSource;
    }


    public static RecipesRepository getInstance(RecipeRemoteDataSource remoteDataSource, RecipesLocalDataSource localDataSource, FirestoreDataSource firestoreDataSource) {
        if (productsRepository == null) {
            productsRepository = new RecipesRepository(remoteDataSource, localDataSource, firestoreDataSource);
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

    public Single<Boolean> isRecipeExistInFavorite(String recipeId) {
        return localDataSource.isRecipeExistInFavorite(recipeId);
    }

    public Single<Boolean> isRecipeExistInPlan(String title) {
        return localDataSource.isRecipeExistInFavorite(title);
    }

    public Single<RecipeResponse> searchRecipesByCategory(String category) {
        return remoteDataSource.searchRecipesByCategory(category);
    }

    public Single<RecipeResponse> searchRecipesByCountry(String country) {
        return remoteDataSource.searchRecipesByCountry(country);
    }

    public Single<RecipeResponse> searchRecipesByIngredient(String ingredient) {
        return remoteDataSource.searchRecipesByIngredient(ingredient);
    }

    public Single<RecipeResponse> searchRecipeByName(String name) {
        return remoteDataSource.searchRecipeByName(name);
    }

    // firestore
    public Completable saveFavoriteRecipe(String userId, String recipeId, Recipe recipe) {
        return firestoreDataSource.saveFavoriteRecipe(userId, recipeId, recipe);
    }

    public Single<Recipe> getFavoriteRecipe(String userId, String recipeId) {
        return firestoreDataSource.getFavoriteRecipe(userId, recipeId);
    }

}
