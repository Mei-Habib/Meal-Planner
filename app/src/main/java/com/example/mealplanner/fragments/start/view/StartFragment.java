package com.example.mealplanner.fragments.start.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.example.mealplanner.helpers.SnackBar;
import com.example.mealplanner.auth.AuthRepository;
import com.example.mealplanner.fragments.start.presenter.StartPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;


public class StartFragment extends Fragment implements StartView {
    private static final int REQUEST_CODE = 9001;
    private MaterialButton signupWithEmail;
    private MaterialButton signupWithGoogle;
    private TextView skip;
    private TextView signin;
    private StartPresenter presenter;
    private GoogleSignInClient googleSignInClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signupWithEmail = view.findViewById(R.id.button_signupWithEmail);
        signupWithGoogle = view.findViewById(R.id.button_continueWithGoogle);
        signin = view.findViewById(R.id.tv_SigninClickable);
        skip = view.findViewById(R.id.button_skip);
        presenter = new StartPresenter(requireContext(), AuthRepository.getInstance(), this);
        setUpGoogle();
        onClick();
    }

    private void onClick() {
        signupWithEmail.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_startFragment_to_registerFragment));
        signin.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_startFragment_to_loginFragment));
        skip.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.action_startFragment_to_homeFragment));
        signupWithGoogle.setOnClickListener(v -> signInWithGoogle());
    }

    private void setUpGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.WEB_ID))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);
    }

    private void signInWithGoogle() {
        googleSignInClient.revokeAccess().addOnCompleteListener(task -> {
            Intent signUpIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signUpIntent, REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.signInWIthGoogle(account);
            } catch (ApiException exception) {
                showError(exception.getMessage());
            }
        }
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
        Navigation.findNavController(requireView()).navigate(R.id.action_startFragment_to_homeFragment);
    }
}
