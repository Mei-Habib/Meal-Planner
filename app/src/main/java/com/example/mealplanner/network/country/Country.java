package com.example.mealplanner.network.country;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("strArea")
    private String country;

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return
                "Country{" +
                        "country = '" + country + '\'' +
                        "}";
    }
}