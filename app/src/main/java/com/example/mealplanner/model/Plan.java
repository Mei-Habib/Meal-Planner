package com.example.mealplanner.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mealplanner.model.recipes.Recipe;

@Entity(tableName = "plan_table")
public class Plan {
    @PrimaryKey(autoGenerate = true)
    private int planId;
    private String recipeTitle;
    private String recipeThumbnail;
    private String planDate;

    public Plan() {
    }

    public Plan(String title, String thumbnail, String date) {
        recipeTitle = title;
        recipeThumbnail = thumbnail;
        planDate = date;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeThumbnail() {
        return recipeThumbnail;
    }

    public void setRecipeThumbnail(String recipeThumbnail) {
        this.recipeThumbnail = recipeThumbnail;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

}
