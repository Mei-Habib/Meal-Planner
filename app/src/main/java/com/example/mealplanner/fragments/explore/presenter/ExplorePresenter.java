package com.example.mealplanner.fragments.explore.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.explore.view.ExploreView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.categories.CategoryResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ExplorePresenter {

    private RecipesRepository repo;
    private ExploreView view;

    public ExplorePresenter(RecipesRepository repo, ExploreView view) {
        this.repo = repo;
        this.view = view;
    }

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
