package com.example.mealplanner.network;

import android.util.Log;

import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.Country;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.ingredients.Ingredient;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.randommeal.RandomMeal;
import com.example.mealplanner.model.randommeal.RandomMealResponse;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.model.recipes.RecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRemoteDataSource {

    public static final String BASE_URL = "https://www.themealdb.com/";
    public static final String TAG = "RecipeClient";
    private RecipeService service;

    public RecipeRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RecipeService.class);
    }

    public void getCategories(NetworkCallback<List<Category>> networkCallback) {
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

    public void getIngredients(NetworkCallback<List<Ingredient>> networkCallback) {
        Log.i(TAG, "getDataOverNetwork: ");
        Call<IngredientResponse> call = service.getIngredients();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getIngredients().get(1));
                if (response.body() != null) {
                    networkCallback.onSuccessResult(response.body().getIngredients());
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallback.onFailureResult(throwable.getMessage().toString());
                throwable.printStackTrace();
            }
        });
    }


}
