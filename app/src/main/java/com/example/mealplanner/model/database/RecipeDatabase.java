package com.example.mealplanner.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealplanner.model.recipes.Recipe;

@Database(entities = {Recipe.class}, version = 1)
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
