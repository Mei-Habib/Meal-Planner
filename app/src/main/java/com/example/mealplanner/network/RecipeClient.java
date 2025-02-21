package com.example.mealplanner.network;

import android.util.Log;

import com.example.mealplanner.network.categories.CategoryResponse;
import com.example.mealplanner.network.country.Country;
import com.example.mealplanner.network.country.CountryResponse;
import com.example.mealplanner.network.randommeal.RandomMeal;
import com.example.mealplanner.network.randommeal.RandomMealResponse;
import com.example.mealplanner.network.recipes.Recipe;
import com.example.mealplanner.network.recipes.RecipeResponse;

import java.util.List;

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

    public void getCategories(NetworkCallback networkCallback) {
        Log.i(TAG, "getDataOverNetwork: ");
        Call<CategoryResponse> call = service.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getCategories().get(1));
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

    public void getRandomMeal(NetworkCallback<List<RandomMeal>> networkCallback) {
        Log.i(TAG, "getDataOverNetwork: ");
        Call<RandomMealResponse> call = service.getRandomMeal();
        call.enqueue(new Callback<RandomMealResponse>() {
            @Override
            public void onResponse(Call<RandomMealResponse> call, Response<RandomMealResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getMeal());
                if (response.body() != null) {
                    networkCallback.onSuccessResult(response.body().getMeal());
                }
            }

            @Override
            public void onFailure(Call<RandomMealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallback.onFailureResult(throwable.getMessage().toString());
                throwable.printStackTrace();
            }
        });
    }

    public void getCountries(NetworkCallback<List<Country>> networkCallback) {
        Log.i(TAG, "getDataOverNetwork: ");
        Call<CountryResponse> call = service.getCountries();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getCountries().get(1));
                if (response.body() != null) {
                    networkCallback.onSuccessResult(response.body().getCountries());
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallback.onFailureResult(throwable.getMessage().toString());
                throwable.printStackTrace();
            }
        });
    }

    public void getRecipes(NetworkCallback<List<Recipe>> networkCallback) {
        Log.i(TAG, "getDataOverNetwork: ");
        Call<RecipeResponse> call = service.getRecipes();
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getRecipes().get(1));
                if (response.body() != null) {
                    networkCallback.onSuccessResult(response.body().getRecipes());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallback.onFailureResult(throwable.getMessage().toString());
                throwable.printStackTrace();
            }
        });
    }


}
