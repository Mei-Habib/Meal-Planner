package com.example.mealplanner.fragments.explore.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.fragments.explore.presenter.ExplorePresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.countries.Country;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.ingredients.Ingredient;
import com.example.mealplanner.network.RecipeRemoteDataSource;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment implements ExploreView {
    private static final String TAG = "ExploreFragment";
    private SearchView searchView;
    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private ExploreAdapter adapter;
    private RecipesRepository repository;
    private ExplorePresenter presenter;
    private static final String CATEGORIES = "Categories";
    private static final String COUNTRIES = "Countries";
    private static final String INGREDIENTS = "Ingredients";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search_view);
        chipGroup = view.findViewById(R.id.chip_group);
        recyclerView = view.findViewById(R.id.recyclerView_explore);
        adapter = new ExploreAdapter(getContext(), 0, new ArrayList<>(0));
        recyclerView.setAdapter(adapter);
        repository = RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(getContext()));
        presenter = new ExplorePresenter(repository, this);

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnClickListener(v -> {
                if (chip.getText().toString().equals(CATEGORIES)) {
                    presenter.getCategories();
                } else if (chip.getText().toString().equals(COUNTRIES)) {
                    presenter.getCountries();
                } else if (chip.getText().toString().equals(INGREDIENTS)) {
                    presenter.getIngredients();
                }
            });
        }
    }

    @Override
    public void showCategories(List<Category> categories) {
        adapter.updateList(1, categories);
    }

    @Override
    public void showCountries(List<Country> countries) {
        adapter.updateList(2, countries);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        adapter.updateList(3, ingredients);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, "showError: " + message);
    }
}
