package com.example.mealplanner.fragments.start.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class StartFragment extends Fragment {
    private static final int REQUEST_CODE = 9001;
    private MaterialButton signupWithEmail;
    private MaterialButton signupWithGoogle;
    private TextView signin;
    private FirebaseAuth auth;
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
        auth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.WEB_ID))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        signupWithEmail.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_startFragment_to_registerFragment));

        signupWithGoogle.setOnClickListener(v -> {
            signInWithGoogle();
        });

        signin.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_startFragment_to_loginFragment));
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
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(getContext(), "Login Failed " + e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        Navigation.findNavController(getView()).navigate(R.id.action_startFragment_to_recipesFragment);
                    else
                        Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                });
    }
}
