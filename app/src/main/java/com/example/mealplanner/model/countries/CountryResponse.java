package com.example.mealplanner.model.countries;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName("meals")
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

}