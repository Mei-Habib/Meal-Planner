package com.example.mealplanner.fragments.profile.presenter;

import android.content.Context;

import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.fragments.profile.view.ProfileView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class ProfilePresenter {
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;
    private ProfileView view;
    private Context context;
    private SharedPreferenceDataSource sharedPreferenceDataSource;

    public ProfilePresenter(Context context, ProfileView view, String webId) {
        this.context = context;
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webId)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(FirebaseApp.getInstance().getApplicationContext(), gso);
        sharedPreferenceDataSource = SharedPreferenceDataSource.getInstance(context);
    }

    public void logout() {
        mAuth.signOut();
        googleSignInClient.signOut();
        clearUser();
    }


    public void clearUser() {
        sharedPreferenceDataSource.clearUser();
        view.showInformation("Logged out successfully");
        view.navigateToStart();
    }

}
