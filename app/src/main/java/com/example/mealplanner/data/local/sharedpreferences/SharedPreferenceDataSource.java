package com.example.mealplanner.data.local.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mealplanner.model.User;
import com.google.gson.Gson;

public class SharedPreferenceDataSource {
    public static final String SHARED_PREF = "MealPlanner";
    public static final String USER = "user";
    private static SharedPreferenceDataSource instance;
    private final SharedPreferences sharedPreferences;

    private SharedPreferenceDataSource(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferenceDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceDataSource(context);
        }
        return instance;
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(USER, userJson);
        editor.apply();
    }

    public User getUser() {
        String userJson = sharedPreferences.getString(USER, null);
        Gson gson = new Gson();
        return userJson != null ? gson.fromJson(userJson, User.class) : null;
    }

    public void clearUser() {
        sharedPreferences.edit().remove(USER).apply();
    }
}
