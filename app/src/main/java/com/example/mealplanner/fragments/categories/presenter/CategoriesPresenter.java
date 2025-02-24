package com.example.mealplanner.fragments.categories.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.categories.view.CategoriesView;
import com.example.mealplanner.fragments.recipes.view.RecipesView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.categories.CategoryResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoriesPresenter {
    private RecipesRepository repo;
    private CategoriesView view;

    public CategoriesPresenter(RecipesRepository repo, CategoriesView view) {
        this.repo = repo;
        this.view = view;
    }

    // Remote Data
    @SuppressLint("CheckResult")
    public void getCategories() {
        repo.getCategories()
                .subscribeOn(Schedulers.io())
                .map(CategoryResponse::getCategories)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.showCategories(list),
                        error -> view.showError(error.getMessage())
                );
    }
}
