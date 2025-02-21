package com.example.mealplanner.fragments.recipes.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.fragments.categories.view.CategoriesAdapter;
import com.example.mealplanner.network.NetworkCallback;
import com.example.mealplanner.network.RecipeClient;
import com.example.mealplanner.network.categories.Category;
import com.example.mealplanner.network.randommeal.RandomMeal;

import java.util.List;

public class RecipesFragment extends Fragment implements NetworkCallback<List<Category>> {

    private static final String TAG = "RecipesFragment";
    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private RecipeClient recipeClient;
    private ImageView randomRecipeImageVIew;
    private ImageView gradientImageView;
    private TextView randomRecipeTitle;
    private TextView randomRecipeDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        randomRecipeImageVIew = view.findViewById(R.id.imv_random_recipe);
        gradientImageView = view.findViewById(R.id.imv_gradient);
        randomRecipeTitle = view.findViewById(R.id.tv_random_recipe_title);
        randomRecipeDescription = view.findViewById(R.id.tv_random_recipe_description);
        categoriesRecyclerView = view.findViewById(R.id.rv_categories);
        recipeClient = new RecipeClient();
        recipeClient.getCategories(new NetworkCallback<List<Category>>() {
            @Override
            public void onSuccessResult(List<Category> categories) {
                Log.i(TAG, "onSuccessResult - Categories: " + categories.get(1));
                categoriesAdapter = new CategoriesAdapter(getContext(), categories);
                categoriesRecyclerView.setAdapter(categoriesAdapter);
            }

            @Override
            public void onFailureResult(String message) {

            }
        });

        recipeClient.getRandomMeal(new NetworkCallback<List<RandomMeal>>() {
            @Override
            public void onSuccessResult(List<RandomMeal> randomMeal) {
                Log.i(TAG, "onSuccessResult - RandomMeal: " + randomMeal.get(0));
                randomRecipeTitle.setText(randomMeal.get(0).getTitle());
//                randomRecipeDescription.setText(randomMeal.get(0).getTags());
                Glide.with(getContext()).load(randomMeal.get(0).getThumbnail())
                        .apply(new RequestOptions().override(200, 200))
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(randomRecipeImageVIew);
            }

            @Override
            public void onFailureResult(String message) {
                Log.e(TAG, "onFailureResult: " + message);
            }
        });
    }

    @Override
    public void onSuccessResult(List<Category> result) {

    }

    @Override
    public void onFailureResult(String message) {

    }


}
