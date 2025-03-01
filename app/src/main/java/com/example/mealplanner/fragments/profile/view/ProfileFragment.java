package com.example.mealplanner.fragments.profile.view;

import android.os.Bundle;
import android.view.Gravity;
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

import com.example.mealplanner.R;
import com.example.mealplanner.helpers.SnackBar;
import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.profile.presenter.ProfilePresenter;
import com.google.android.material.button.MaterialButton;

public class ProfileFragment extends Fragment implements ProfileView {
    private MaterialButton logout;
    private ProfilePresenter presenter;
    private ConstraintLayout guestView;
    private Group userView;
    private TextView signUpButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout = view.findViewById(R.id.button_logout);
        guestView = view.findViewById(R.id.guestV);
        userView = view.findViewById(R.id.userV);
        signUpButton = view.findViewById(R.id.signUpButton);

        if (SharedPreferenceDataSource.getInstance(requireContext()).getUser() == null) {
            guestView.setVisibility(View.VISIBLE);
        } else {
            userView.setVisibility(View.VISIBLE);
        }

        presenter = new ProfilePresenter(requireContext(), this, getString(R.string.WEB_ID));
        onClick();


    }

    private void onClick() {
        logout.setOnClickListener(v -> {
            presenter.logout();
        });

        signUpButton.setOnClickListener(v->Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_startFragment));
    }

    @Override
    public void showInformation(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.green, Gravity.BOTTOM);
    }

    @Override
    public void navigateToStart() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_startFragment);
    }
}
