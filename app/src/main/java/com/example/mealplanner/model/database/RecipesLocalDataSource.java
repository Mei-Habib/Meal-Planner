package com.example.mealplanner.model.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class RecipesLocalDataSource {
    private final RecipeDAO dao;

    public RecipesLocalDataSource(Context context) {
        RecipeDatabase db = RecipeDatabase.getInstance(context);
        dao = db.getRecipeDAO();
    }

    public Observable<List<Recipe>> getStoredRecipes() {
        return dao.getRecipes();
    }

    public Completable insertRecipe(Recipe recipe) {
        return dao.insertRecipe(recipe);
    }

    public Completable deleteRecipe(Recipe recipe) {
        return dao.deleteRecipe(recipe);
    }

}
