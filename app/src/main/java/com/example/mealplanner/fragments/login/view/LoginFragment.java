package com.example.mealplanner.fragments.login.view;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.example.mealplanner.helpers.SnackBar;
import com.example.mealplanner.auth.AuthRepository;
import com.example.mealplanner.fragments.login.presenter.LoginPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment implements LoginView {
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton signinButton;
    private ImageButton back;
    private String email;
    private String password;
    private LoginPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEditText = view.findViewById(R.id.email_signin);
        passwordEditText = view.findViewById(R.id.password_signin);
        signinButton = view.findViewById(R.id.button_signin);
        back = view.findViewById(R.id.button_backSignin);
        presenter = new LoginPresenter(requireContext(), AuthRepository.getInstance(), this);
        onClick();
    }

    private void onClick() {
        signinButton.setOnClickListener(v -> extractUserData());
        back.setOnClickListener(v -> Navigation.findNavController(requireView()).navigateUp());

    }

    private void extractUserData() {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        presenter.validateData(email, password);
    }

    @Override
    public void showInformation(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.green, Gravity.BOTTOM);
    }

    @Override
    public void showError(String message) {
        SnackBar.showCustomSnackBar(requireActivity(), message, R.color.red, Gravity.BOTTOM);
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment);
    }
}
