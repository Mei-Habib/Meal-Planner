package com.example.mealplanner.fragments.recipedetails.presenter;

import com.example.mealplanner.fragments.favorite.view.FavoriteView;
import com.example.mealplanner.fragments.recipedetails.view.RecipeDetailsView;
import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.recipes.Recipe;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeDetailsPresenter {
    private final RecipeDetailsView view;
    private final RecipesRepository repo;

    public RecipeDetailsPresenter(RecipesRepository repo, RecipeDetailsView view) {
        this.repo = repo;
        this.view = view;
    }

    public void insertRecipe(Recipe recipe) {
        repo.insertRecipe(recipe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    public void deleteRecipe(Recipe recipe) {
        repo.deleteRecipe(recipe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    public void insertPlan(Plan plan) {
        repo.insertPlan(plan).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
