package com.example.mealplanner.fragments.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton signinButton;
    private TextView signupTextView;
    private ImageView googleImageView;
    private ImageView facebookImageView;
    private String email;
    private String password;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

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
        signupTextView = view.findViewById(R.id.tv_signup_clickable);
        googleImageView = view.findViewById(R.id.imv_google);
        mAuth = FirebaseAuth.getInstance();

        signinButton.setOnClickListener(v -> {
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();

            if (email.isEmpty() || email.isBlank()) {
                Toast.makeText(getContext(), "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty() || password.isBlank()) {
                Toast.makeText(getContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                user = mAuth.getCurrentUser();
                                //updateUI(user);
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_recipesFragment);
                                Toast.makeText(getContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        });


    }
}
