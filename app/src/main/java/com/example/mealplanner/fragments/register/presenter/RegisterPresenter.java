package com.example.mealplanner.fragments.register.presenter;

import android.content.Context;

import com.example.mealplanner.auth.AuthRepository;
import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.register.view.RegisterView;
import com.example.mealplanner.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterPresenter {

    private AuthRepository authRepo;
    private RegisterView view;
    private Context context;


    public RegisterPresenter(Context context, AuthRepository authRepo, RegisterView view) {
        this.view = view;
        this.authRepo = authRepo;
        this.context = context;
    }

    public void validateData(String name, String email, String password) {
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            signUpWithEmail(name, email, password);
        } else {
            view.showError("Please fill all fields");
        }
    }

    public void signUpWithEmail(String name, String email, String password) {
        authRepo.signUpWithEmail(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null) {
                            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            firebaseUser.updateProfile(profileUpdate);
                            User user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail());
                            saveUserCredentials(user);
                            view.showInformation("Account created successfully");
                        }

                    } else {
                        view.showError("Failed: " + task.getException().getMessage());
                    }
                });

    }

    private void saveUserCredentials(User user) {
        SharedPreferenceDataSource.getInstance(context).saveUser(user);
        view.navigateToHome();
    }
}
