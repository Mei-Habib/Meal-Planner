package com.example.mealplanner.fragments.home.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.helpers.SnackBar;
import com.example.mealplanner.fragments.categories.CategoriesAdapter;
import com.example.mealplanner.fragments.explore.presenter.ExplorePresenter;
import com.example.mealplanner.fragments.explore.view.ExploreView;
import com.example.mealplanner.fragments.home.presenter.RandomRecipePresenter;
import com.example.mealplanner.fragments.home.presenter.HomePresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.countries.Country;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.ingredients.Ingredient;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.FirestoreDataSource;
import com.example.mealplanner.network.RecipeRemoteDataSource;

import java.util.List;

public class HomeFragment extends Fragment implements HomeView, ExploreView, HomeAdapter.OnRecipeClickListener {
    private RecyclerView categoriesRecyclerView;
    private RecyclerView recipesRecyclerView;
    private HomeAdapter recipesAdapter;
    private HomePresenter recipesPresenter;
    private RandomRecipePresenter randomRecipePresenter;
    private ExplorePresenter explorePresenter;
    private ConnectivityManager.NetworkCallback networkCallback;
    private ConnectivityManager connectivityManager;
    private CardView inspirationCardView;
    private ImageView randomRecipeImageVIew;
    private TextView randomRecipeTitle;
    private ConstraintLayout mainView;
    private Group noInternetView;
    private boolean internetConnectionLost = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inspirationCardView = view.findViewById(R.id.card_inspiration);
        randomRecipeImageVIew = view.findViewById(R.id.imv_random_recipe);
        mainView = view.findViewById(R.id.mainView);
        noInternetView = view.findViewById(R.id.noInternetView);
        randomRecipeTitle = view.findViewById(R.id.tv_random_recipe_title);

        recipesRecyclerView = view.findViewById(R.id.rv_recipes);

        if (!isInternetAvailable()) {
            showNoInternetView();
        } else {
            initViews();
        }

    }

    private void initViews() {
        RecipesRepository repository = RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(getContext()), new FirestoreDataSource());
        recipesPresenter = new HomePresenter(repository, this);
        recipesPresenter.getRecipes();

        randomRecipePresenter = new RandomRecipePresenter(repository, this);
        randomRecipePresenter.getRandomRecipe();

        explorePresenter = new ExplorePresenter(repository, this);
        explorePresenter.getCategories();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            }
        }
        return false;
    }

    @Override
    public void onRecipeClickListener(Recipe recipe) {
        HomeFragmentDirections.ActionHomeFragmentToRecipeDetailsFragment action
                = HomeFragmentDirections.actionHomeFragmentToRecipeDetailsFragment(recipe);
        Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        recipesAdapter = new HomeAdapter(getContext(), this, recipes);
        recipesRecyclerView.setAdapter(recipesAdapter);
    }

    @Override
    public void showRandomRecipe(List<Recipe> randomRecipe) {
        randomRecipeTitle.setText(randomRecipe.get(0).getTitle());
        if (isAdded() && getContext() != null) {
            Glide.with(getContext()).load(randomRecipe.get(0).getThumbnail())
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(R.drawable.placeholder)
                    .into(randomRecipeImageVIew);
        }
        inspirationCardView.setOnClickListener(view -> {
            HomeFragmentDirections.ActionHomeFragmentToRecipeDetailsFragment action
                    = HomeFragmentDirections.actionHomeFragmentToRecipeDetailsFragment(randomRecipe.get(0));
            Navigation.findNavController(getView()).navigate(action);
        });
    }

    private void reloadView() {
        SnackBar.showCustomSnackBar(requireActivity(), getString(R.string.internet_connection_restored), R.color.green, Gravity.TOP);
        noInternetView.setVisibility(View.GONE);
        initViews();
    }

    private void registerNetworkCallback() {
        connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                requireActivity().runOnUiThread(() -> {
                    if (internetConnectionLost) {
                        reloadView();
                        internetConnectionLost = false;
                    }
                });
            }

            @Override
            public void onLost(@NonNull Network network) {
                requireActivity().runOnUiThread(() ->
                {
                    internetConnectionLost = true;
                    showNoInternetView();
                });
            }
        };

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }

    @Override
    public void onStart() {
        super.onStart();
        registerNetworkCallback();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (connectivityManager != null && networkCallback != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    @Override
    public void showRecipesByCategory(List<Recipe> recipes) {
        // recipes screen
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showCategories(List<Category> categories) {

    }

    @Override
    public void showCountries(List<Country> countries) {

    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {

    }


    public void showNoInternetView() {
        SnackBar.showCustomSnackBar(requireActivity(), getString(R.string.no_internet_connection), R.color.red, Gravity.TOP);
        mainView.setVisibility(View.GONE);
        noInternetView.setVisibility(View.VISIBLE);
        internetConnectionLost = true;
    }
}
