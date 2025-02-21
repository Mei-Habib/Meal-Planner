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
import com.example.mealplanner.network.NetworkCallback;
import com.example.mealplanner.network.RecipeRemoteDataSource;
import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.countries.Country;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class ExploreFragment extends Fragment implements NetworkCallback {

    private static final String TAG = "ExploreFragment";
    private SearchView searchView;
    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private ExploreAdapter adapter;
    private RecipeRemoteDataSource recipeRemoteDataSource;
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
        recipeRemoteDataSource = new RecipeRemoteDataSource();

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnClickListener(v -> {
                if (chip.getText().toString().equals(CATEGORIES)) {
                    recipeRemoteDataSource.getCategories(new NetworkCallback<List<Category>>() {
                        @Override
                        public void onSuccessResult(List<Category> categories) {
                            Log.i(TAG, "onSuccessResult: " + categories.get(1));
                            adapter = new ExploreAdapter(getContext(), 1, categories);
                            recyclerView.setAdapter(adapter);
//                            adapter.updateList(categories);
                        }

                        @Override
                        public void onFailureResult(String message) {
                            Log.e(TAG, "onFailureResult: " + message);
                        }
                    });
                } else if (chip.getText().toString().equals(COUNTRIES)) {
                    recipeRemoteDataSource.getCountries(new NetworkCallback<List<Country>>() {
                        @Override
                        public void onSuccessResult(List<Country> countries) {
                            Log.i(TAG, "onSuccessResult: " + countries.get(1));
                            adapter = new ExploreAdapter(getContext(), 2, countries);
                            recyclerView.setAdapter(adapter);
//                            adapter.updateList(countries);
                        }

                        @Override
                        public void onFailureResult(String message) {
                            Log.e(TAG, "onFailureResult: " + message);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onSuccessResult(Object result) {

    }

    @Override
    public void onFailureResult(String message) {

    }
}
