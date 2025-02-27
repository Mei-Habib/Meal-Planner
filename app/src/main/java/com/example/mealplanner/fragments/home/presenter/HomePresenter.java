package com.example.mealplanner.fragments.home.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.home.view.HomeView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.recipes.RecipeResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {

    private RecipesRepository repo;
    private HomeView view;

    public HomePresenter(RecipesRepository repo, HomeView view) {
        this.repo = repo;
        this.view = view;
    }

    // Remote Data
    @SuppressLint("CheckResult")
    public void getRecipes() {
        repo.getRecipes()
                .subscribeOn(Schedulers.io())
                .map(RecipeResponse::getRecipes)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.showRecipes(list),
                        error -> view.showError(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getRecipesByCategory(String category) {
        repo.searchRecipesByCategory(category)
                .subscribeOn(Schedulers.io())
                .map(RecipeResponse::getRecipes)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.showRecipesByCategory(list),
                        error -> view.showError(error.getMessage())
                );
    }
}
