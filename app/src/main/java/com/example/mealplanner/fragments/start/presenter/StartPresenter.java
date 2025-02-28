package com.example.mealplanner.fragments.start.presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.example.mealplanner.auth.AuthRepository;
import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.register.view.RegisterView;
import com.example.mealplanner.fragments.start.view.StartView;
import com.example.mealplanner.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class StartPresenter {
    AuthRepository authRepo;
    private StartView view;
    private Context context;

    public StartPresenter(Context context, AuthRepository authRepo, StartView view) {
        this.view = view;
        this.authRepo = authRepo;
        this.context = context;
    }

    public void signInWIthGoogle(GoogleSignInAccount account) {
        authRepo.signInWithGoogle(account).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                saveUserCredentials();
                view.showInformation("Login Successfully");
            } else {
                view.showError(task.getException().getMessage());
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
