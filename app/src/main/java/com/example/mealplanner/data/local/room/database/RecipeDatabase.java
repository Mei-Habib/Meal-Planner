package com.example.mealplanner.data.local.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.recipes.Recipe;

@Database(entities = {Recipe.class, Plan.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    private static final String DB_NAME = "product_database";
    static RecipeDatabase dbInstance = null;

    public abstract RecipeDAO getRecipeDAO();

    public static synchronized RecipeDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context, RecipeDatabase.class, DB_NAME).build();
        }
        return dbInstance;
    }
}
