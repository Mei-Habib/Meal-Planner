//package com.example.mealplanner;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.button.MaterialButton;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class SignupActivity extends AppCompatActivity {
//
//    private static final String TAG = "SignupActivity";
//    private TextInputEditText usernameEditText;
//    private TextInputEditText emailEditText;
//    private TextInputEditText passwordEditText;
//    private MaterialButton signupButton;
//    private TextView signinTextView;
//    private String username;
//    private String email;
//    private String password;
//    private FirebaseAuth mAuth;
//    private FirebaseUser user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.forget_password);
//
//        usernameEditText = findViewById(R.id.username);
//        emailEditText = findViewById(R.id.email_signup);
//        passwordEditText = findViewById(R.id.password_signup);
//        signupButton = findViewById(R.id.button_signup);
//        signinTextView = findViewById(R.id.tv_signin_clickable);
//        mAuth = FirebaseAuth.getInstance();
//
//
//        signupButton.setOnClickListener(view -> {
//            username = usernameEditText.getText().toString();
//            email = emailEditText.getText().toString();
//            password = passwordEditText.getText().toString();
//
//            if (email.isEmpty() || email.isBlank()) {
//                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (password.isEmpty() || password.isBlank()) {
//                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Log.d(TAG, "createUserWithEmail:success");
//                                user = mAuth.getCurrentUser();
//                                //updateUI(user);
//                                Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                Toast.makeText(SignupActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
//                                //updateUI(null);
//                            }
//                        }
//                    });
//        });
//
//        signinTextView.setOnClickListener(view -> {
//            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
//            startActivity(intent);
//            finish();
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
//}