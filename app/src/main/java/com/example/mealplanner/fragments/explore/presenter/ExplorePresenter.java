package com.example.mealplanner.fragments.explore.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.explore.view.ExploreView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.ingredients.IngredientResponse;

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

    @SuppressLint("CheckResult")
    public void getCountries() {
        repo.getCountries()
                .subscribeOn(Schedulers.io())
                .map(CountryResponse::getCountries)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.showCountries(list),
                        error -> view.showError(error.getMessage())
                );
    }

    @SuppressLint("CheckResult")
    public void getIngredients() {
        repo.getIngredients()
                .subscribeOn(Schedulers.io())
                .map(IngredientResponse::getIngredients)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> view.showIngredients(list),
                        error -> view.showError(error.getMessage())
                );
    }
}
