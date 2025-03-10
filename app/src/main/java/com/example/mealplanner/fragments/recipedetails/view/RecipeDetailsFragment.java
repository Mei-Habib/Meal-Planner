package com.example.mealplanner.fragments.recipedetails.view;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.helpers.SnackBar;
import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.recipedetails.presenter.RecipeDetailsPresenter;
import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.FirestoreDataSource;
import com.example.mealplanner.network.RecipeRemoteDataSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Date;
import java.util.Locale;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsView {
    private static final String TAG = "RecipeDetailsFragment";
    private ImageView thumbnail;
    private TextView title;
    ImageButton bookmark;
    ImageButton datePicker;
    private TextView cuisine;
    private TextView instructions;
    private YouTubePlayerView youTubePlayerView;
    private TextView serve;
    private ImageButton plus;
    private ImageButton minus;
    private ImageButton back;
    private Group guestView;
    private Recipe recipe;
    private RecyclerView recyclerView;
    private RecipeDetailsAdapter adapter;
    private RecipeDetailsPresenter presenter;
    String videoId;
    int count = 1;
    Boolean isFav = false;

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
        cuisine = view.findViewById(R.id.tv_cuisine);
        instructions = view.findViewById(R.id.tv_instructions);
        youTubePlayerView = view.findViewById(R.id.recipeVideoView);
        serve = view.findViewById(R.id.tv_serve);
        plus = view.findViewById(R.id.button_plus);
        minus = view.findViewById(R.id.button_minus);
        bookmark = view.findViewById(R.id.button_add_favorite);
        datePicker = view.findViewById(R.id.button_calendar);
        back = view.findViewById(R.id.button_back);
        guestView = view.findViewById(R.id.guestView);
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_nav);
        presenter = new RecipeDetailsPresenter(requireContext(), RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(getContext()), new FirestoreDataSource()), this);

        initializeViews();
        adapter = new RecipeDetailsAdapter(getContext(), this, count, recipe);
        recyclerView.setAdapter(adapter);
        onClick();
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
        if (SharedPreferenceDataSource.getInstance(requireContext()).getUser() == null) {
            guestView.setVisibility(View.GONE);
        }

        recipe = RecipeDetailsFragmentArgs.fromBundle(getArguments()).getRecipe();
        presenter.isRecipeExistInFavorite(recipe);
        presenter.isRecipeExistInPlan(recipe);
        title.setText(recipe.getTitle());
        cuisine.setText(recipe.getCuisine());
        serve.setText(count + " serves");

        if (recipe.getInstructions() != null) {
            for (String s : recipe.getInstructions().split("\\r")) {
                if (!s.trim().isEmpty())
                    instructions.append(s);
            }
        }


        if (isAdded() && getContext() != null) {
            Glide.with(requireContext()).load(recipe.getThumbnail())
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(R.drawable.placeholder)
                    .into(thumbnail);
        }

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


            adapter = new RecipeDetailsAdapter(getContext(), this, count, recipe);
            recyclerView.setAdapter(adapter);
        }
    }

    private void onClick() {
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

        back.setOnClickListener(v -> Navigation.findNavController(getView()).navigateUp());

        bookmark.setOnClickListener(v -> {
//            if (isInternetAvailable()){
            if (isFav) {
                presenter.deleteRecipe(recipe);
            } else {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                presenter.insertRecipe(recipe);
                presenter.saveFavoriteRecipe(userId, recipe.getId(), recipe); // firestore
            }
        });
//            else{
//                showError("no internet");
//            }
//        });


        datePicker.setOnClickListener(v -> {
            long today = MaterialDatePicker.todayInUtcMilliseconds();
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTimeInMillis(today);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            long endDate = calendar.getTimeInMillis();
            // validate date selection
            CalendarConstraints.DateValidator validator = new CalendarConstraints.DateValidator() {
                @Override
                public boolean isValid(long date) {
                    return date >= today && date <= endDate;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(android.os.Parcel dest, int flags) {
                }
            };

            CalendarConstraints constraints = new CalendarConstraints.Builder()
                    .setStart(today)
                    .setEnd(endDate)
                    .setValidator(validator)
                    .build();

            MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .setCalendarConstraints(constraints)
                    .setTheme(R.style.CustomDatePicker)
                    .build();

            picker.show(requireActivity().getSupportFragmentManager(), "tag");
            picker.addOnPositiveButtonClickListener(selection -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
                String formatedDate = simpleDateFormat.format(new Date(selection));
                Plan plan = new Plan(recipe.getTitle(), recipe.getThumbnail(), formatedDate);
                presenter.insertPlan(plan);
            });
        });

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
    public void showMessage(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.green, Gravity.BOTTOM);
    }

    @Override
    public void showError(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.red, Gravity.BOTTOM);
    }

    @Override
    public void toggleFavBtn(Boolean isExist) {
        isFav = isExist;
        if (isExist) {
            bookmark.setImageResource(R.drawable.bookmark_fill_ic);
        } else {
            bookmark.setImageResource(R.drawable.ic_bookmark);
        }

    }

    @Override
    public void toggleCalendarBtn(Boolean isExist) {
        if (isExist) {
            datePicker.setImageResource(R.drawable.calendar_fill_ic);
        } else {
            datePicker.setImageResource(R.drawable.ic_calendar);
        }
    }

    @Override
    public void onRemovedFromFavSuccess(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.green, Gravity.BOTTOM);
        isFav = false;
        bookmark.setImageResource(R.drawable.ic_bookmark);
    }

    @Override
    public void onAddedToFavSuccess(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.green, Gravity.BOTTOM);
        isFav = true;
        bookmark.setImageResource(R.drawable.bookmark_fill_ic);
    }

    @Override
    public void onAddedToPlanSuccess(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.green, Gravity.BOTTOM);
    }
}
