package com.example.mealplanner.fragments.favorite.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.favorite.view.FavoriteView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.recipes.Recipe;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter {
    private final FavoriteView view;
    private final RecipesRepository repo;

    public FavoritePresenter(RecipesRepository repo, FavoriteView view) {
        this.repo = repo;
        this.view = view;
    }


    @SuppressLint("CheckResult")
    public void getStoredRecipes() {
        repo.getStoredRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showFavoriteRecipes,
                        error -> view.showError(error.getMessage())
                );
    }
}
