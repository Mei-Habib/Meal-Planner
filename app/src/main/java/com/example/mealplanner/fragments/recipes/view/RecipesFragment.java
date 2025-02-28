package com.example.mealplanner.fragments.recipes.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.fragments.recipes.presenter.RecipesPresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class RecipesFragment extends Fragment implements RecipesView {

    private static final String TAG = "RecipesFragment";
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RecipesAdapter adapter;
    private RecipesPresenter presenter;
    private Disposable searchDisposable;


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
        String key = RecipesFragmentArgs.fromBundle(getArguments()).getKey();
        int searchBy = RecipesFragmentArgs.fromBundle(getArguments()).getSearchBy();
        presenter.getRecipes("", key, searchBy);

        // handle system back pressed
        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Navigation.findNavController(view).popBackStack();
                    }
                }
        );

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchDisposable != null) {
                    searchDisposable.dispose();
                }

                searchDisposable = Observable.just(newText)
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(query -> {
                            presenter.getRecipes(query, key, searchBy);
                        });

                return false;
            }
        });

    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        if (recipes == null || recipes.isEmpty()) {
            this.showError("No recipes found");
            return;
        }
        adapter.updateList(recipes);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, "showError: " + message);
    }
}
