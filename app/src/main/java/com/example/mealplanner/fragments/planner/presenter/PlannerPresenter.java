package com.example.mealplanner.fragments.planner.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.planner.view.PlannerView;
import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.recipes.Recipe;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerPresenter {
    private RecipesRepository repo;
    private PlannerView view;

    public PlannerPresenter(RecipesRepository repo, PlannerView view) {
        this.repo = repo;
        this.view = view;
    }

    public void deletePlan(Plan plan) {
        repo.deletePlan(plan).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @SuppressLint("CheckResult")
    public void searchRecipeByName(String name) {
        repo.searchRecipeByName(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                list -> view.showRecipe(list.getRecipes().get(0)),
                error -> view.showError(error.getMessage())
        );
    }

    @SuppressLint("CheckResult")
    public void getPlans(String date) {
        repo.getPlans(date).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                list -> view.showPlans(list),
                error -> view.showError(error.getMessage())
        );
    }
}
