package com.example.mealplanner.fragments.recipedetails.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.favorite.view.FavoriteView;
import com.example.mealplanner.fragments.recipedetails.view.RecipeDetailsView;
import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.recipes.Recipe;

import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeDetailsPresenter {
    private final RecipeDetailsView view;
    private final RecipesRepository repo;
    private final Context context;

    public RecipeDetailsPresenter(Context context, RecipesRepository repo, RecipeDetailsView view) {
        this.repo = repo;
        this.view = view;
        this.context = context;
    }

    @SuppressLint("CheckResult")
    public void insertRecipe(Recipe recipe) {
        repo.insertRecipe(recipe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> {
                    view.onAddedToFavSuccess("Recipe added to favorite successfully");
                },
                throwable -> view.showError(throwable.getMessage())
        );
    }

    @SuppressLint("CheckResult")
    public void deleteRecipe(Recipe recipe) {
        repo.deleteRecipe(recipe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> {
                    view.onRemovedFromFavSuccess("Recipe removed from favorite successfully");
                },
                throwable -> view.showError(throwable.getMessage())
        );

    }

    public void searchRecipeByName(String name){
        repo.searchRecipeByName(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @SuppressLint("CheckResult")
    public void isRecipeExistInFavorite(Recipe recipe) {
        repo.isRecipeExistInFavorite(recipe.getId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                isExist -> view.toggleFavBtn(isExist),
                error -> view.showError(error.getMessage())
        );
    }

    @SuppressLint("CheckResult")
    public void isRecipeExistInPlan(Recipe recipe) {
        repo.isRecipeExistInPlan(recipe.getTitle()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                view::toggleCalendarBtn,
                error -> view.showError(error.getMessage())
        );
    }

    @SuppressLint("CheckResult")
    public void insertPlan(Plan plan) {
        repo.insertPlan(plan).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> view.onAddedToPlanSuccess("Recipe added to plan successfully"),
                throwable -> view.showError(throwable.getMessage())
        );
    }

    // firestore
    @SuppressLint("CheckResult")
    public void saveFavoriteRecipe(String userId, String recipeId, Recipe recipe) {
        repo.saveFavoriteRecipe(userId, recipeId, recipe).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    public void getFavoriteRecipe(String userId, String recipeId) {
        repo.getFavoriteRecipe(userId, recipeId);
    }


}
