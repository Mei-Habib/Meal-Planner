package com.example.mealplanner.fragments.register;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private TextInputEditText usernameEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton signupButton;
    private ImageButton back;
    private String username;
    private String email;
    private String password;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

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
        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(v -> {
            username = usernameEditText.getText().toString();
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

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity(), task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            user = mAuth.getCurrentUser();
                            Toast.makeText(getContext(), "Account Created", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        back.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_startFragment));

//        google.setOnClickListener(v -> {
//            CredentialManager credentialManager = CredentialManager.create(getContext());
//            // Instantiate a Google sign-in request
//            GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
//                    .setFilterByAuthorizedAccounts(true)
//                    .setServerClientId(getString(R.string.default_web_client_id))
//                    .build();
//
//            // Create the Credential Manager request
//            GetCredentialRequest request = new GetCredentialRequest.Builder()
//                    .addCredentialOption(googleIdOption)
//                    .build();
//        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
            Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_recipesFragment);
    }
}
