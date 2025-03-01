package com.example.mealplanner.fragments.profile.view;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.example.mealplanner.SnackBar;
import com.example.mealplanner.fragments.profile.presenter.ProfilePresenter;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment implements ProfileView {
    private MaterialButton logout;
    private ProfilePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout = view.findViewById(R.id.button_logout);
        presenter = new ProfilePresenter(requireContext(), this, getString(R.string.WEB_ID));
        logout.setOnClickListener(v -> {
            presenter.logout();
        });
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
