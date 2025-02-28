package com.example.mealplanner.fragments.login.view;

public interface LoginView {
    void showInformation(String message);

    void showError(String message);

    void navigateToHome();
}
