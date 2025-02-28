package com.example.mealplanner.fragments.start.view;

public interface StartView {
    void showInformation(String message);

    void showError(String message);

    void navigateToHome();
}
