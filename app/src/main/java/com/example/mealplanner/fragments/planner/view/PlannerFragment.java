package com.example.mealplanner.fragments.planner.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.fragments.planner.presenter.PlannerPresenter;
import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.model.database.RecipesLocalDataSource;
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
        presenter = new PlannerPresenter(RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(requireContext())), this);
        adapter = new PlannerAdapter(requireContext(), this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

//        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
//            String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
//            Log.i(TAG, "onDateChangeListener: " + selectedDate);
//            presenter.getPlans(selectedDate);
//        });

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", date.getYear(), date.getMonth() + 1, date.getDay());
                Log.i(TAG, "onDateChangeListener: " + selectedDate);
                presenter.getPlans(selectedDate);
            }
        });
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
