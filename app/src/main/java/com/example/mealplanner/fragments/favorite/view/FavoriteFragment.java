package com.example.mealplanner.fragments.favorite.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.favorite.presenter.FavoritePresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.FirestoreDataSource;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteView, FavoriteAdapter.OnRecipeClickListener {

    private static final String TAG = "FavoriteFragment";
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private FavoritePresenter presenter;
    private List<Recipe> storedRecipes;
    private ConstraintLayout guestView;
    private TextView title;
    private TextView signUp;
    private Group emptyGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.favTitle);
        recyclerView = view.findViewById(R.id.rv_favorite);
        guestView = view.findViewById(R.id.guestView);
        signUp = view.findViewById(R.id.signUpBtn);
        emptyGroup = view.findViewById(R.id.emptyGroup);
        presenter = new FavoritePresenter(RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(getContext()), new FirestoreDataSource()), this);

        if (SharedPreferenceDataSource.getInstance(requireContext()).getUser() == null) {
            title.setVisibility(View.GONE);
            guestView.setVisibility(View.VISIBLE);
            signUp.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_favoriteFragment_to_startFragment));
        } else {
            title.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new FavoriteAdapter(getContext(), this, new ArrayList<>());
            recyclerView.setAdapter(adapter);
            presenter.getStoredRecipes();

        }

    }

    @Override
    public void showFavoriteRecipes(List<Recipe> favoriteRecipes) {
        if (!favoriteRecipes.isEmpty()) {
            adapter.updateList(favoriteRecipes);
        } else {
            emptyGroup.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

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
