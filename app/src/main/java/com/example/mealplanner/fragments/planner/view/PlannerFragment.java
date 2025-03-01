package com.example.mealplanner.fragments.planner.view;

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
import com.example.mealplanner.fragments.planner.presenter.PlannerPresenter;
import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.RecipeRemoteDataSource;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlannerFragment extends Fragment implements PlannerView, PlannerAdapter.OnPlanClickListener {

    private static final String TAG = "PlannerFragment";
    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private PlannerAdapter adapter;
    private PlannerPresenter presenter;
    private ConstraintLayout guestView;
    private Group userView;
    private TextView signUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calendarView);
        recyclerView = view.findViewById(R.id.plansRecyclerView);
        guestView = view.findViewById(R.id.guestView);
        userView = view.findViewById(R.id.userView);
        signUp = view.findViewById(R.id.signUp);
        presenter = new PlannerPresenter(RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(requireContext())), this);
        adapter = new PlannerAdapter(requireContext(), this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        if (SharedPreferenceDataSource.getInstance(requireContext()).getUser() == null) {
            guestView.setVisibility(View.VISIBLE);
        } else {
            userView.setVisibility(View.VISIBLE);
        }

        onClick();

    }

    private void onClick() {
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", date.getYear(), date.getMonth() + 1, date.getDay());
            presenter.getPlans(selectedDate);
        });

        signUp.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_plannerFragment_to_startFragment));
    }

    @Override
    public void showPlans(List<Plan> plans) {
        adapter.updateList(plans);
    }

    @Override
    public void showRecipe(Recipe recipe) {
        PlannerFragmentDirections.ActionPlannerFragmentToRecipeDetailsFragment action = PlannerFragmentDirections.actionPlannerFragmentToRecipeDetailsFragment(recipe);
        Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, "showError: " + message);
    }

    @Override
    public void onPlanClickListener(Plan plan) {
        // call api to get recipe by name
        presenter.searchRecipeByName(plan.getRecipeTitle());
    }

    @Override
    public void onDeleteClickListener(Plan plan) {
        presenter.deletePlan(plan);
    }
}
