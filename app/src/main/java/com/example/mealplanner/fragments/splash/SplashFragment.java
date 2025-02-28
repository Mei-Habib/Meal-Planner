package com.example.mealplanner.fragments.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplanner.R;
import com.example.mealplanner.data.local.sharedpreferences.SharedPreferenceDataSource;
import com.example.mealplanner.model.User;

public class SplashFragment extends Fragment {
    private static final int SPLASH_DELAY = 6000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = SharedPreferenceDataSource.getInstance(requireContext()).getUser();
        new Handler().postDelayed(() -> {
            if (user != null)
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
            else
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_startFragment);

        }, SPLASH_DELAY);
    }
}
