package com.example.mealplanner.network.country;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName("meals")
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "countries = '" + countries + '\'' +
                        "}";
    }
}