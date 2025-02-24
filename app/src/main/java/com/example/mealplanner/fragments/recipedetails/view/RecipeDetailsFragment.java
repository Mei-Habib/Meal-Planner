package com.example.mealplanner.fragments.recipedetails.view;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.fragments.recipedetails.presenter.RecipeDetailsPresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.RecipeRemoteDataSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsView {
    private static final String TAG = "RecipeDetailsFragment";
    private ImageView thumbnail;
    private TextView title;
    ImageButton bookmark;
    ImageButton calendar;
    private TextView cookingTime;
    private TextView cuisine;
    private TextView instructions;
    private YouTubePlayerView youTubePlayerView;
    private TextView serve;
    private ImageButton plus;
    private ImageButton minus;
    private ImageButton back;
    private Recipe recipe;
    private RecyclerView recyclerView;
    private RecipeDetailsAdapter adapter;
    private RecipeDetailsPresenter presenter;
    String videoId;
    int count = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_recipeIngredients);
        thumbnail = view.findViewById(R.id.imv_recipe);
        title = view.findViewById(R.id.tv_recipeTitle);
        cookingTime = view.findViewById(R.id.tv_cookingTime);
        cuisine = view.findViewById(R.id.tv_cuisine);
        instructions = view.findViewById(R.id.tv_instructions);
        youTubePlayerView = view.findViewById(R.id.recipeVideoView);
        serve = view.findViewById(R.id.tv_serve);
        plus = view.findViewById(R.id.button_plus);
        minus = view.findViewById(R.id.button_minus);
        bookmark = view.findViewById(R.id.button_add_favorite);
        calendar = view.findViewById(R.id.button_calendar);
        back = view.findViewById(R.id.button_back);

        presenter = new RecipeDetailsPresenter(RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(getContext())), this);
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
        serve.setText(count + " serves");
        for (String s : recipe.getInstructions().split("\\r")) {
            if (!s.trim().isEmpty())
                instructions.append("Step " + s);
        }

        Glide.with(getContext()).load(recipe.getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.placeholder)
                .into(thumbnail);

        // video view
        String videoUrl = recipe.getYoutubeURL();
        videoId = extractVideoIdFromUrl(videoUrl);
        if (videoId != null) {
            getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0);
                    Log.i(TAG, "onReady: " + videoId);
                }

                @Override
                public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                    super.onError(youTubePlayer, error);
                    Log.e(TAG, "YouTube Player Error: " + error.toString());
                }

            });

            plus.setOnClickListener(view -> {
                count++;
                serve.setText(count + " serves");
                adapter.updateList(count);

            });

            minus.setOnClickListener(view -> {
                if (count > 1) {
                    count--;
                    serve.setText(count + " serves");
                    adapter.updateList(count);
                }

            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(getView()).navigate(R.id.action_recipeDetailsFragment_to_recipesFragment);
                }
            });

            bookmark.setOnClickListener(view -> {
                presenter.insertRecipe(recipe);
//                if (bookmark.getDrawable().getConstantState() == ContextCompat.getDrawable(getContext(), R.drawable.save_ic).getConstantState()) {
//                    bookmark.setImageResource(R.drawable.save_filled_ic);
//                    presenter.insertRecipe(recipe);
//
//                } else {
//                    bookmark.setImageResource(R.drawable.save_ic);
//                    presenter.deleteRecipe(recipe);
//                }

            });

            adapter = new RecipeDetailsAdapter(getContext(), this, count, recipe);
            recyclerView.setAdapter(adapter);
            BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_nav);
//            bottomNav.setVisibility(View.GONE);
        }
    }

    private String extractVideoIdFromUrl(String youtubeUrl) {
        String videoId = null;
        if (youtubeUrl != null && !youtubeUrl.trim().isEmpty() && youtubeUrl.startsWith("https://www.youtube.com/")) {
            String[] urlParts = youtubeUrl.split("v=");
            if (urlParts.length > 1) {
                videoId = urlParts[1];
                int ampersandPosition = videoId.indexOf('&');
                if (ampersandPosition != -1) {
                    videoId = videoId.substring(0, ampersandPosition);
                }
            }
        }
        return videoId;
    }

    @Override
    public void showRecipe(Recipe recipe) {

    }

    @Override
    public void showError(String message) {

    }
}
