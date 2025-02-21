package com.example.mealplanner.model.countries;

import com.example.mealplanner.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

    @SerializedName("strArea")
    private String country;

    private List<Integer> flags = List.of(R.drawable.american, R.drawable.british, R.drawable.canadian, R.drawable.chinese, R.drawable.croatian, R.drawable.dutch, R.drawable.egyptian, R.drawable.filipino, R.drawable.french, R.drawable.greek, R.drawable.indian, R.drawable.irish, R.drawable.italian, R.drawable.jamaican, R.drawable.japanese, R.drawable.kenyan, R.drawable.malasiyan, R.drawable.mexican, R.drawable.moroccan, R.drawable.norwegian, R.drawable.polish, R.drawable.portuguese, R.drawable.russian, R.drawable.spanish, R.drawable.thai, R.drawable.tunisian, R.drawable.turkish, R.drawable.ukrainian, R.drawable.uruguayan, R.drawable.vietnamese);

    public String getCountry() {
        return country;
    }

    public List<Integer> getThumbnail() {
        return flags;
    }

    @Override
    public String toString() {
        return
                "Country{" +
                        "country = '" + country + '\'' +
                        "}";
    }
}