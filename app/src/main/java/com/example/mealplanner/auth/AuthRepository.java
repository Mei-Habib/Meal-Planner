package com.example.mealplanner.auth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthRepository {

    private FirebaseAuthDataSource firebaseAuthDataSource;
    private static AuthRepository instance;

    private AuthRepository() {
        firebaseAuthDataSource = FirebaseAuthDataSource.getInstance();
    }

    public static synchronized AuthRepository getInstance() {
        if (instance == null) {
            instance = new AuthRepository();
        }
        return instance;
    }

    public Task<AuthResult> loginWithEmail(String email, String password) {
        return firebaseAuthDataSource.getAuth().signInWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signUpWithEmail(String email, String password) {
        return firebaseAuthDataSource.getAuth().createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signInWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        return firebaseAuthDataSource.getAuth().signInWithCredential(credential);
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuthDataSource.getCurrentUser();
    }
}
