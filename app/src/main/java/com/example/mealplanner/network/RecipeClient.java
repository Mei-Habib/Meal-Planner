package com.example.mealplanner.network;

import android.util.Log;

import com.example.mealplanner.network.categories.CategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeClient {

    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String TAG = "RecipeClient";
    private RecipeService service;

    public RecipeClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RecipeService.class);
    }

    public void getDataOverNetwork(NetworkCallback networkCallback) {
        Log.i(TAG, "getDataOverNetwork: ");
        Call<CategoryResponse> call = service.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i(TAG, "onResponse: ");
                if (response.body() != null) {
                    networkCallback.onSuccessResult(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallback.onFailureResult(throwable.getMessage().toString());
                throwable.printStackTrace();
            }
        });

    }
}
