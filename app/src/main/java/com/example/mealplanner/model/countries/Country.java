package com.example.mealplanner.model.countries;

import com.example.mealplanner.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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