package com.example.mealplanner.fragments.register.view;

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
import com.example.mealplanner.fragments.register.presenter.RegisterPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment implements RegisterView {
    private TextInputEditText usernameEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton signupButton;
    private ImageButton back;
    private String username;
    private String email;
    private String password;
    private RegisterPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameEditText = view.findViewById(R.id.username);
        emailEditText = view.findViewById(R.id.email_signup);
        passwordEditText = view.findViewById(R.id.password_signup);
        signupButton = view.findViewById(R.id.button_signup);
        back = view.findViewById(R.id.button_backSignup);

        presenter = new RegisterPresenter(requireContext(), AuthRepository.getInstance(), this);
        onCLick();

    }

    private void onCLick() {
        signupButton.setOnClickListener(v -> extractUserData());
        back.setOnClickListener(v -> Navigation.findNavController(requireView()).navigateUp());
    }

    private void extractUserData() {
        username = usernameEditText.getText().toString();
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        presenter.validateData(username, email, password);
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
        Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_homeFragment);
    }
}
