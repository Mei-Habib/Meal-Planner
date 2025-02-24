package com.example.mealplanner.fragments.recipes.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.recipes.view.RandomRecipeView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.recipes.RecipeResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomRecipePresenter {
    private RecipesRepository repo;
    private RandomRecipeView view;

    public RandomRecipePresenter(RecipesRepository repo, RandomRecipeView view) {
        this.repo = repo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void getRandomRecipe() {
        repo.getRandomRecipe()
                .subscribeOn(Schedulers.io())
                .map(RecipeResponse::getRecipes)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.showRandomRecipe(list),
                        error -> view.showError(error.getMessage())
                );
    }
}
