package com.example.mealplanner.fragments.recipes.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.fragments.recipes.presenter.RecipesPresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment implements RecipesView {

    private static final String TAG = "RecipesFragment";
    private SearchView searchView;
    private RecyclerView recyclerView;
    private TextView searchByItem;
    private RecipesAdapter adapter;
    private RecipesPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchViewRecipe);
        recyclerView = view.findViewById(R.id.recyclerViewRecipes);
        adapter = new RecipesAdapter(requireContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        presenter = new RecipesPresenter(RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(requireContext())), this);
        String title = RecipesFragmentArgs.fromBundle(getArguments()).getTitle();
        int searchBy = RecipesFragmentArgs.fromBundle(getArguments()).getSearchBy();
        presenter.getRecipes(title, searchBy);

        // handle system back pressed
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Navigation.findNavController(view).popBackStack();
                    }
                }
        );

    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        adapter.updateList(recipes);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, "showError: " + message);
    }
}
