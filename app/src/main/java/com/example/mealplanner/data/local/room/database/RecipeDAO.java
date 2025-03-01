package com.example.mealplanner.data.local.room.database;

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
import io.reactivex.rxjava3.core.Single;

@Dao
public interface RecipeDAO {

    // Recipe
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRecipe(Recipe recipe);

    @Delete
    Completable deleteRecipe(Recipe recipe);

    @Query("SELECT * FROM recipe_table")
    Observable<List<Recipe>> getRecipes();

    @Query("SELECT EXISTS(SELECT * FROM recipe_table WHERE id = :recipeId)")
    Single<Boolean> isRecipeExistInFavorite(String recipeId);

    // Plan
    @Insert
    Completable insertPlan(Plan plan);

    @Delete
    Completable deletePlan(Plan plan);

    @Query("SELECT * FROM plan_table WHERE planDate LIKE :date")
    Observable<List<Plan>> getPlans(String date);

    @Query("SELECT EXISTS(SELECT * FROM plan_table WHERE recipeTitle = :title)")
    Single<Boolean> isRecipeExistInPlan(String title);
}
