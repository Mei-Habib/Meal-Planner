package com.example.mealplanner.model.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

public class RecipesLocalDataSource {
    private RecipeDAO dao;
    private LiveData<List<Recipe>> storedProducts;

    public RecipesLocalDataSource(Context context) {
        RecipeDatabase db = RecipeDatabase.getInstance(context);
        dao = db.getRecipeDAO();
    }

    public LiveData<List<Recipe>> getStoredProducts() {
        storedProducts = dao.getRecipes();
        return storedProducts;
    }

    public void addProduct(Recipe recipe) {
        new Thread() {
            @Override
            public void run() {
                dao.insertRecipe(recipe);
            }
        }.start();
    }

    public void removeProduct(Recipe recipe) {
        new Thread() {
            @Override
            public void run() {
                dao.deleteRecipe(recipe);
            }
        }.start();
    }

}
