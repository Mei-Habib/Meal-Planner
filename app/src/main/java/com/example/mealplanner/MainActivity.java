package com.example.mealplanner;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.mealplanner.fragments.recipedetails.view.RecipeDetailsFragment;
import com.example.mealplanner.fragments.recipes.view.RecipesAdapter;
import com.example.mealplanner.model.recipes.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    NavController navController;
    ;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.registerFragment || navDestination.getId() == R.id.loginFragment || navDestination.getId() == R.id.recipeDetailsFragment || navDestination.getId() == R.id.startFragment || navDestination.getId() == R.id.splashFragment)
                bottomNavigationView.setVisibility(View.GONE);
            else
                bottomNavigationView.setVisibility(View.VISIBLE);
        });

    }

}