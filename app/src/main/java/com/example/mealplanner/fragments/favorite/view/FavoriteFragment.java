package com.example.mealplanner.fragments.favorite.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.fragments.favorite.presenter.FavoritePresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteView, FavoriteAdapter.OnRecipeClickListener {

    private static final String TAG = "FavoriteFragment";
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private FavoritePresenter presenter;
    private List<Recipe> storedRecipes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_favorite);
        presenter = new FavoritePresenter(RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(getContext())), this);
        adapter = new FavoriteAdapter(getContext(), this, new ArrayList<>());
        presenter.getStoredRecipes();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFavoriteRecipes(List<Recipe> favoriteRecipes) {
        adapter.updateList(favoriteRecipes);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, "showError: " + message);
    }

    @Override
    public void onRecipeClickListener(Recipe recipe) {
        FavoriteFragmentDirections.ActionFavoriteFragmentToRecipeDetailsFragment action = FavoriteFragmentDirections.actionFavoriteFragmentToRecipeDetailsFragment(recipe);
        Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void onDeleteClickListener(Recipe recipe) {
        presenter.deleteRecipe(recipe);
    }
}
