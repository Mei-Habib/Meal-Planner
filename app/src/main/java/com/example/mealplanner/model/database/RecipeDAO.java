package com.example.mealplanner.model.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecipe(Recipe recipe);

    @Delete
    public void deleteRecipe(Recipe recipe);

    @Query("SELECT * FROM recipe_table")
    public LiveData<List<Recipe>> getRecipes();
}
