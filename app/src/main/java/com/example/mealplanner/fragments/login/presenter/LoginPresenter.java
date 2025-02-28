package com.example.mealplanner.fragments.login.presenter;

import android.content.Context;

import com.example.mealplanner.auth.AuthRepository;
import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.login.view.LoginView;
import com.example.mealplanner.model.User;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    private AuthRepository authRepo;
    private LoginView view;
    private Context context;


    public LoginPresenter(Context context, AuthRepository authRepo, LoginView view) {
        this.view = view;
        this.authRepo = authRepo;
        this.context = context;
    }

    public void validateData(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            loginWithEmail(email, password);
        } else {
            view.showError("Please fill all fields");
        }
    }

    public void loginWithEmail(String email, String password) {
        authRepo.loginWithEmail(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                saveUserCredentials();
                view.showInformation("Log in Successfully");
            } else {
                view.showError("Failed" + task.getException().getMessage());
            }

        });

    }

    private void saveUserCredentials() {
        FirebaseUser currentUser = authRepo.getCurrentUser();
        User user = new User(currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail());
        SharedPreferenceDataSource.getInstance(context).saveUser(user);
        view.navigateToHome();
    }
}

