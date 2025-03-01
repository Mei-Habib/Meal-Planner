package com.example.mealplanner.helpers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.view.Gravity;

import com.example.mealplanner.R;

public class NetworkMonitor {

    private final ConnectivityManager connectivityManager;
    private final ConnectivityManager.NetworkCallback networkCallback;
    private final Activity activity;
    private boolean isConnected;
    public NetworkMonitor(Activity activity) {
        this.activity = activity;
        connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        isConnected = checkCurrentNetwork();

        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                if (!isConnected) {
                    showSnackbar("Connected to the Internet", R.color.green);
                    isConnected = true;
                }
            }

            @Override
            public void onLost(Network network) {
                super.onLost(network);
                if (isConnected) {
                    showSnackbar("Internet connection lost", R.color.red);
                    isConnected = false; // Update state
                }
            }
        };
    }

    public void registerNetworkCallback() {
        NetworkRequest request = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();
        connectivityManager.registerNetworkCallback(request, networkCallback);
    }

    public void unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    private void showSnackbar(String message, int color) {
        activity.runOnUiThread(() ->
                SnackBar.showCustomSnackBar(activity, message, color, Gravity.TOP)
        );
    }

    private boolean checkCurrentNetwork() {
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }
}
