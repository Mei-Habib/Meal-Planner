package com.example.mealplanner.network;

import android.util.Log;

import com.example.mealplanner.model.categories.CategoryResponse;
import com.example.mealplanner.model.countries.CountryResponse;
import com.example.mealplanner.model.ingredients.IngredientResponse;
import com.example.mealplanner.model.randommeal.RandomMealResponse;
import com.example.mealplanner.model.recipes.RecipeResponse;


import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(RecipeService.class);
    }

    public Single<CategoryResponse> getCategories() {
        Log.i(TAG, "getDataOverNetwork: ");
        return service.getCategories();
//        call.enqueue(new Callback<CategoryResponse>() {
//            @Override
//            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
//                Log.i(TAG, "onResponse: " + response.body().getCategories().get(1));
//                if (response.body() != null) {
//                    networkCallback.onSuccessResult(response.body().getCategories());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
//                Log.i(TAG, "onFailure: ");
//                networkCallback.onFailureResult(throwable.getMessage().toString());
//                throwable.printStackTrace();
//            }
//        });
    }

    public Single<RandomMealResponse> getRandomMeal() {
        Log.i(TAG, "getDataOverNetwork: ");
        return service.getRandomMeal();
//        call.enqueue(new Callback<RandomMealResponse>() {
//            @Override
//            public void onResponse(Call<RandomMealResponse> call, Response<RandomMealResponse> response) {
//                Log.i(TAG, "onResponse: " + response.body().getMeal());
//                if (response.body() != null) {
//                    networkCallback.onSuccessResult(response.body().getMeal());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RandomMealResponse> call, Throwable throwable) {
//                Log.i(TAG, "onFailure: ");
//                networkCallback.onFailureResult(throwable.getMessage().toString());
//                throwable.printStackTrace();
//            }
//        });
    }

    public Single<CountryResponse> getCountries() {
        Log.i(TAG, "getDataOverNetwork: ");
        return service.getCountries();
//        call.enqueue(new Callback<CountryResponse>() {
//            @Override
//            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
//                Log.i(TAG, "onResponse: " + response.body().getCountries().get(1));
//                if (response.body() != null) {
//                    networkCallback.onSuccessResult(response.body().getCountries());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CountryResponse> call, Throwable throwable) {
//                Log.i(TAG, "onFailure: ");
//                networkCallback.onFailureResult(throwable.getMessage().toString());
//                throwable.printStackTrace();
//            }
//        });
    }

    public Single<RecipeResponse> getRecipes() {
        Log.i(TAG, "getDataOverNetwork: ");
        return service.getRecipes();
    }

    public Single<IngredientResponse> getIngredients() {
        Log.i(TAG, "getDataOverNetwork: ");
        return service.getIngredients();
//        call.enqueue(new Callback<IngredientResponse>() {
//            @Override
//            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
//                Log.i(TAG, "onResponse: " + response.body().getIngredients().get(1));
//                if (response.body() != null) {
//                    networkCallback.onSuccessResult(response.body().getIngredients());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
//                Log.i(TAG, "onFailure: ");
//                networkCallback.onFailureResult(throwable.getMessage().toString());
//                throwable.printStackTrace();
//            }
//        });
    }


}
