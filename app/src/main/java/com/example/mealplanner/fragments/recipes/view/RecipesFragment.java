package com.example.mealplanner.fragments.recipes.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.fragments.categories.view.CategoriesAdapter;
import com.example.mealplanner.network.NetworkCallback;
import com.example.mealplanner.network.RecipeClient;
import com.example.mealplanner.network.categories.Category;

import java.util.List;

public class RecipesFragment extends Fragment implements NetworkCallback{

    private static final String TAG = "RecipesFragment";
    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private RecipeClient recipeClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoriesRecyclerView = view.findViewById(R.id.rv_categories);
        recipeClient = new RecipeClient();
        recipeClient.getCategories(new NetworkCallback<List<Category>>() {
            @Override
            public void onSuccessResult(List<Category> result) {

            }

            @Override
            public void onFailureResult(String message) {

            }
        });
    }

//    @Override
//    public void onSuccessResult(List<Category> categories) {
//        categoriesAdapter = new CategoriesAdapter(getContext(), categories);
//        categoriesRecyclerView.setAdapter(categoriesAdapter);
//    }


//    @Override
//    public void onSuccessResult(Object result) {
//        categoriesAdapter = new CategoriesAdapter(getContext(), (List<Category>) result);
//        categoriesRecyclerView.setAdapter(categoriesAdapter);
//    }

    @Override
    public void onSuccessResult(Object result) {

    }

    @Override
    public void onFailureResult(String message) {
        Log.e(TAG, "onFailureResult: " + message);
    }
}
