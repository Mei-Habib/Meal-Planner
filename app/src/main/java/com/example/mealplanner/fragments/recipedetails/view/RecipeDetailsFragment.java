package com.example.mealplanner.fragments.recipedetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.model.recipes.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class RecipeDetailsFragment extends Fragment {

    private View bottomSheet;
    private ImageView thumbnail;
    private TextView title;
    private TextView cookingTime;
    private TextView cuisine;
    private Recipe recipe;
    private RecyclerView recyclerView;
    private RecipeDetailsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheet = view.findViewById(R.id.standard_bottom_sheet);
        recyclerView = view.findViewById(R.id.recyclerView_recipeIngredients);
        thumbnail = view.findViewById(R.id.imv_recipe);
        title = view.findViewById(R.id.tv_recipeTitle);
        cookingTime = view.findViewById(R.id.tv_cookingTime);
        cuisine = view.findViewById(R.id.tv_cuisine);

        initializeViews();

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

    private void initializeViews() {
        recipe = RecipeDetailsFragmentArgs.fromBundle(getArguments()).getRecipe();
        title.setText(recipe.getTitle());
        cuisine.setText(recipe.getCuisine());
        Glide.with(getContext()).load(recipe.getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .into(thumbnail);
        adapter = new RecipeDetailsAdapter(getContext(), recipe);
        recyclerView.setAdapter(adapter);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_nav);
        bottomNav.setVisibility(View.GONE);
    }
}
