package com.example.mealplanner.fragments.recipes.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.recipes.view.RecipesView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.RecipeResponse;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipesPresenter {

    private RecipesRepository repo;
    private RecipesView view;

    public RecipesPresenter(RecipesRepository repo, RecipesView view) {
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
}
