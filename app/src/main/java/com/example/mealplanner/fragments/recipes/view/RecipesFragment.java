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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.fragments.categories.presenter.CategoriesPresenter;
import com.example.mealplanner.fragments.categories.view.CategoriesAdapter;
import com.example.mealplanner.fragments.categories.view.CategoriesView;
import com.example.mealplanner.fragments.recipes.presenter.RandomRecipePresenter;
import com.example.mealplanner.fragments.recipes.presenter.RecipesPresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.List;

public class RecipesFragment extends Fragment implements RecipesView, RandomRecipeView, CategoriesView, RecipesAdapter.OnRecipeClickListener {

    private static final String TAG = "RecipesFragment";
    private RecyclerView categoriesRecyclerView;
    private RecyclerView recipesRecyclerView;
    private RecipesAdapter recipesAdapter;
    private RecipesPresenter recipesPresenter;
    private RandomRecipePresenter randomRecipePresenter;
    private CategoriesPresenter categoriesPresenter;
    private CategoriesAdapter categoriesAdapter;
    private CardView inspirationCardView;
    private ImageView randomRecipeImageVIew;
    private TextView randomRecipeTitle;
//    private TextView randomRecipeDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inspirationCardView = view.findViewById(R.id.card_inspiration);
        randomRecipeImageVIew = view.findViewById(R.id.imv_random_recipe);
        randomRecipeTitle = view.findViewById(R.id.tv_random_recipe_title);
//        randomRecipeDescription = view.findViewById(R.id.tv_random_recipe_description);
        categoriesRecyclerView = view.findViewById(R.id.rv_categories);
        recipesRecyclerView = view.findViewById(R.id.rv_recipes);

        RecipesRepository repository = RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(getContext()));
        recipesPresenter = new RecipesPresenter(repository, this);
        recipesPresenter.getRecipes();

        randomRecipePresenter = new RandomRecipePresenter(repository, this);
        randomRecipePresenter.getRandomRecipe();

        categoriesPresenter = new CategoriesPresenter(repository, this);
        categoriesPresenter.getCategories();
    }

    @Override
    public void onRecipeClickListener(Recipe recipe) {
        Log.i(TAG, "onRecipeClickListener: " + recipe.getIngredients().get(1));
        RecipesFragmentDirections.ActionRecipesFragmentToRecipeDetailsFragment action
                = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(recipe);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        recipesAdapter = new RecipesAdapter(getContext(), this, recipes);
        recipesRecyclerView.setAdapter(recipesAdapter);
    }

    @Override
    public void showRandomRecipe(List<Recipe> randomRecipe) {
        Log.i(TAG, "showRandomRecipe: " + randomRecipe.get(0));
        randomRecipeTitle.setText(randomRecipe.get(0).getTitle());
//                randomRecipeDescription.setText(randomMeal.get(0).getTags());
        Glide.with(getContext()).load(randomRecipe.get(0).getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .into(randomRecipeImageVIew);

        inspirationCardView.setOnClickListener(view -> {
            RecipesFragmentDirections.ActionRecipesFragmentToRecipeDetailsFragment action
                    = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(randomRecipe.get(0));
            Navigation.findNavController(getView()).navigate(action);
        });
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoriesAdapter = new CategoriesAdapter(getContext(), categories);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, "showError: " + message);
    }
}
