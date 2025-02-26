package com.example.mealplanner.model.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.recipes.Recipe;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface RecipeDAO {

    // Recipe
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRecipe(Recipe recipe);

    @Delete
    Completable deleteRecipe(Recipe recipe);

    @Query("SELECT * FROM recipe_table")
    Observable<List<Recipe>> getRecipes();

    // Plan
    @Insert
    Completable insertPlan(Plan plan);

    @Delete
    Completable deletePlan(Plan plan);

    @Query("SELECT * FROM plan_table WHERE planDate LIKE :date")
    Observable<List<Plan>> getPlans(String date);
}
