package com.example.mealplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealplanner.MainActivity;
import com.example.mealplanner.R;
import com.example.mealplanner.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    private static final String TAG = "SigninActivity";
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);

        emailEditText = findViewById(R.id.email_signin);
        passwordEditText = findViewById(R.id.password_signin);
        signinButton = findViewById(R.id.button_signin);
        signupTextView = findViewById(R.id.tv_signup_clickable);
        googleImageView = findViewById(R.id.imv_google);
        facebookImageView = findViewById(R.id.imv_facebook);
        mAuth = FirebaseAuth.getInstance();

        signinButton.setOnClickListener(view -> {
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();

            if (email.isEmpty() || email.isBlank()) {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty() || password.isBlank()) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                user = mAuth.getCurrentUser();
                                //updateUI(user);
                                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(SigninActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(SigninActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        });

        signupTextView.setOnClickListener(view -> {
            Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });

//        googleImageView.setOnClickListener(view -> {
//            signInRequest = BeginSignInRequest.builder()
//                    .setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions.builder()
//                            .setSupported(true)
//                            // Your server's client ID, not your Android client ID.
//                            .setServerClientId(getString(R.string.default_web_client_id))
//                            // Only show accounts previously used to sign in.
//                            .setFilterByAuthorizedAccounts(true)
//                            .build())
//                    .build();
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
