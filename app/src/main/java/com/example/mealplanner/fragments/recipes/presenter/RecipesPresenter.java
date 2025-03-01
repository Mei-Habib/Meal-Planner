package com.example.mealplanner.fragments.recipes.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.explore.view.ExploreAdapter;
import com.example.mealplanner.fragments.recipes.view.RecipesView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.recipes.RecipeResponse;

import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipesPresenter {
    private RecipesRepository repo;
    private RecipesView view;
    private final String CATEGORIES = "Categories";
    private final String COUNTRIES = "Countries";
    private final String INGREDIENTS = "Ingredients";

    public RecipesPresenter(RecipesRepository repo, RecipesView view) {
        this.repo = repo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void getRecipes(String query, String key, int searchBy) {
        if (searchBy == ExploreAdapter.CATEGORY_LAYOUT) {
            repo.searchRecipesByCategory(key)
                    .subscribeOn(Schedulers.io())
                    .map(RecipeResponse::getRecipes)
                    .map(recipes -> recipes.stream()
                            .filter(recipe -> recipe.getTitle().toLowerCase().contains(query.toLowerCase()))
                            .collect(Collectors.toList()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            list -> view.showRecipes(list),
                            error -> view.showError(error.getMessage())
                    );
        } else if (searchBy == ExploreAdapter.COUNTRY_LAYOUT) {
            repo.searchRecipesByCountry(key)
                    .subscribeOn(Schedulers.io())
                    .map(RecipeResponse::getRecipes)
                    .map(recipes -> recipes.stream()
                            .filter(recipe -> recipe.getTitle().toLowerCase().contains(query.toLowerCase()))
                            .collect(Collectors.toList()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            list -> view.showRecipes(list),
                            error -> view.showError(error.getMessage()));

        } else if (searchBy == ExploreAdapter.INGREDIENT_LAYOUT) {
            repo.searchRecipesByIngredient(key)
                    .subscribeOn(Schedulers.io())
                    .map(RecipeResponse::getRecipes)
                    .map(recipes -> recipes.stream()
                            .filter(recipe -> recipe.getTitle().toLowerCase().contains(query.toLowerCase()))
                            .collect(Collectors.toList()))
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(
                            list -> view.showRecipes(list),
                            error -> view.showError(error.getMessage()));
        }
    }
}
