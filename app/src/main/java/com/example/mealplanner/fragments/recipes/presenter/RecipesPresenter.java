package com.example.mealplanner.fragments.recipes.presenter;

import android.annotation.SuppressLint;

import com.example.mealplanner.fragments.explore.view.ExploreAdapter;
import com.example.mealplanner.fragments.recipes.view.RecipesView;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.ingredients.Ingredient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipesPresenter {
    private RecipesRepository repo;
    private RecipesView view;

    public RecipesPresenter(RecipesRepository repo, RecipesView view) {
        this.repo = repo;
        this.view = view;
    }

//    public void search(String query) {
//        Observable<Ingredient> observable = Observable.fromIterable(ingredientsList);
//        observable
//                .filter(item -> item.getStrIngredient().toLowerCase().contains(query.toLowerCase()))
//                .toList()
//                .subscribe(ingredientDTOS -> view.filterData(ingredientDTOS));
//    }

    @SuppressLint("CheckResult")
    public void getRecipes(String title, int searchBy) {
        if (searchBy == ExploreAdapter.CATEGORY_LAYOUT) {
            repo.searchRecipesByCategory(title).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    list -> view.showRecipes(list.getRecipes()),
                    error -> view.showError(error.getMessage())
            );
        } else if (searchBy == ExploreAdapter.COUNTRY_LAYOUT) {
            repo.searchRecipesByCountry(title).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    list -> view.showRecipes(list.getRecipes()),
                    error -> view.showError(error.getMessage()));

        } else if (searchBy == ExploreAdapter.INGREDIENT_LAYOUT) {
            repo.searchRecipesByIngredient(title).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    list -> view.showRecipes(list.getRecipes()),
                    error -> view.showError(error.getMessage()));
        }
    }
}
