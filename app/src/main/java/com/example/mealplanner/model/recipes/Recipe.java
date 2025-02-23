package com.example.mealplanner.model.recipes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipe_table")
public class Recipe implements Parcelable {
    @SerializedName("strIngredient10")
    private String ingredient10;

    @SerializedName("strIngredient12")
    private String ingredient12;

    @SerializedName("strIngredient11")
    private String ingredient11;

    @SerializedName("strIngredient14")
    private String ingredient14;

    @SerializedName("strCategory")
    private String category;

    @SerializedName("strIngredient13")
    private String ingredient13;

    @SerializedName("strIngredient16")
    private String ingredient16;

    @SerializedName("strIngredient15")
    private String ingredient15;

    @SerializedName("strIngredient18")
    private String ingredient18;

    @SerializedName("strIngredient17")
    private String ingredient17;

    @SerializedName("strArea")
    private String cuisine;

    @SerializedName("strIngredient19")
    private String ingredient19;

    @SerializedName("strTags")
    private String tags;

    @PrimaryKey
    @NonNull
    @SerializedName("idMeal")
    private String id;

    @SerializedName("strInstructions")
    private String instructions;

    @SerializedName("strIngredient1")
    private String ingredient1;

    @SerializedName("strIngredient3")
    private String ingredient3;

    @SerializedName("strIngredient2")
    private String ingredient2;

    @SerializedName("strIngredient20")
    private String ingredient20;

    @SerializedName("strIngredient5")
    private String ingredient5;

    @SerializedName("strIngredient4")
    private String ingredient4;

    @SerializedName("strIngredient7")
    private String ingredient7;

    @SerializedName("strIngredient6")
    private String ingredient6;

    @SerializedName("strIngredient9")
    private String ingredient9;

    @SerializedName("strIngredient8")
    private String ingredient8;

    @SerializedName("strMealThumb")
    private String thumbnail;

    @SerializedName("strMeasure20")
    private String measure20;

    @SerializedName("strYoutube")
    private String youtubeURL;

    @SerializedName("strMeal")
    private String title;

    @SerializedName("strMeasure12")
    private String measure12;

    @SerializedName("strMeasure13")
    private String measure13;

    @SerializedName("strMeasure10")
    private String measure10;

    @SerializedName("strMeasure11")
    private String measure11;

    @SerializedName("strMeasure9")
    private String measure9;

    @SerializedName("strMeasure7")
    private String measure7;

    @SerializedName("strMeasure8")
    private String measure8;

    @SerializedName("strMeasure5")
    private String measure5;

    @SerializedName("strMeasure6")
    private String measure6;

    @SerializedName("strMeasure3")
    private String measure3;

    @SerializedName("strMeasure4")
    private String measure4;

    @SerializedName("strMeasure1")
    private String measure1;

    @SerializedName("strMeasure18")
    private String measure18;

    @SerializedName("strMeasure2")
    private String measure2;

    @SerializedName("strMeasure19")
    private String measure19;

    @SerializedName("strMeasure16")
    private String measure16;

    @SerializedName("strMeasure17")
    private String measure17;

    @SerializedName("strMeasure14")
    private String measure14;

    @SerializedName("strMeasure15")
    private String measure15;


    protected Recipe(Parcel in) {
        ingredient10 = in.readString();
        ingredient12 = in.readString();
        ingredient11 = in.readString();
        ingredient14 = in.readString();
        category = in.readString();
        ingredient13 = in.readString();
        ingredient16 = in.readString();
        ingredient15 = in.readString();
        ingredient18 = in.readString();
        ingredient17 = in.readString();
        cuisine = in.readString();
        ingredient19 = in.readString();
        tags = in.readString();
        id = in.readString();
        instructions = in.readString();
        ingredient1 = in.readString();
        ingredient3 = in.readString();
        ingredient2 = in.readString();
        ingredient20 = in.readString();
        ingredient5 = in.readString();
        ingredient4 = in.readString();
        ingredient7 = in.readString();
        ingredient6 = in.readString();
        ingredient9 = in.readString();
        ingredient8 = in.readString();
        thumbnail = in.readString();
        measure20 = in.readString();
        youtubeURL = in.readString();
        title = in.readString();
        measure12 = in.readString();
        measure13 = in.readString();
        measure10 = in.readString();
        measure11 = in.readString();
        measure9 = in.readString();
        measure7 = in.readString();
        measure8 = in.readString();
        measure5 = in.readString();
        measure6 = in.readString();
        measure3 = in.readString();
        measure4 = in.readString();
        measure1 = in.readString();
        measure18 = in.readString();
        measure2 = in.readString();
        measure19 = in.readString();
        measure16 = in.readString();
        measure17 = in.readString();
        measure14 = in.readString();
        measure15 = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);
        ingredients.add(ingredient5);
        ingredients.add(ingredient6);
        ingredients.add(ingredient7);
        ingredients.add(ingredient8);
        ingredients.add(ingredient9);
        ingredients.add(ingredient10);
        ingredients.add(ingredient11);
        ingredients.add(ingredient12);
        ingredients.add(ingredient13);
        ingredients.add(ingredient14);
        ingredients.add(ingredient15);
        ingredients.add(ingredient16);
        ingredients.add(ingredient17);
        ingredients.add(ingredient18);
        ingredients.add(ingredient19);
        ingredients.add(ingredient20);
        // Remove null values
        ingredients.removeIf(item -> item == null || item.isEmpty());
        return ingredients;
    }

    public List<String> getMeasures() {
        List<String> measures = new ArrayList<>();
        measures.add(measure1);
        measures.add(measure2);
        measures.add(measure3);
        measures.add(measure4);
        measures.add(measure5);
        measures.add(measure6);
        measures.add(measure7);
        measures.add(measure8);
        measures.add(measure9);
        measures.add(measure10);
        measures.add(measure11);
        measures.add(measure12);
        measures.add(measure13);
        measures.add(measure14);
        measures.add(measure15);
        measures.add(measure16);
        measures.add(measure17);
        measures.add(measure18);
        measures.add(measure19);
        measures.add(measure20);
        // Remove null values
        measures.removeIf(item -> item == null || item.isEmpty());
        return measures;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public String getIngredient12() {
        return ingredient12;
    }

    public String getIngredient11() {
        return ingredient11;
    }

    public String getIngredient14() {
        return ingredient14;
    }

    public String getCategory() {
        return category;
    }

    public String getIngredient13() {
        return ingredient13;
    }

    public String getIngredient16() {
        return ingredient16;
    }

    public String getIngredient15() {
        return ingredient15;
    }

    public String getIngredient18() {
        return ingredient18;
    }

    public String getIngredient17() {
        return ingredient17;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getIngredient19() {
        return ingredient19;
    }

    public String getTags() {
        return tags;
    }

    public String getId() {
        return id;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public String getIngredient20() {
        return ingredient20;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getMeasure20() {
        return measure20;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public String getTitle() {
        return title;
    }

    public String getMeasure12() {
        return measure12;
    }

    public String getMeasure13() {
        return measure13;
    }

    public String getMeasure10() {
        return measure10;
    }

    public String getMeasure11() {
        return measure11;
    }

    public String getMeasure9() {
        return measure9;
    }

    public String getMeasure7() {
        return measure7;
    }

    public String getMeasure8() {
        return measure8;
    }

    public String getMeasure5() {
        return measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public String getMeasure3() {
        return measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public String getMeasure1() {
        return measure1;
    }

    public String getMeasure18() {
        return measure18;
    }

    public String getMeasure2() {
        return measure2;
    }

    public String getMeasure19() {
        return measure19;
    }

    public String getMeasure16() {
        return measure16;
    }

    public String getMeasure17() {
        return measure17;
    }

    public String getMeasure14() {
        return measure14;
    }

    public String getMeasure15() {
        return measure15;
    }

    @Override
    public String toString() {
        return
                "Recipe{" +
                        ",category = '" + category + '\'' +
                        ",cuisine = '" + cuisine + '\'' +
                        ",tags = '" + tags + '\'' +
                        ",id = '" + id + '\'' +
                        ",thumbnail = '" + thumbnail + '\'' +
                        ",youtubeURL = '" + youtubeURL + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ingredient10);
        dest.writeString(ingredient12);
        dest.writeString(ingredient11);
        dest.writeString(ingredient14);
        dest.writeString(category);
        dest.writeString(ingredient13);
        dest.writeString(ingredient16);
        dest.writeString(ingredient15);
        dest.writeString(ingredient18);
        dest.writeString(ingredient17);
        dest.writeString(cuisine);
        dest.writeString(ingredient19);
        dest.writeString(tags);
        dest.writeString(id);
        dest.writeString(instructions);
        dest.writeString(ingredient1);
        dest.writeString(ingredient3);
        dest.writeString(ingredient2);
        dest.writeString(ingredient20);
        dest.writeString(ingredient5);
        dest.writeString(ingredient4);
        dest.writeString(ingredient7);
        dest.writeString(ingredient6);
        dest.writeString(ingredient9);
        dest.writeString(ingredient8);
        dest.writeString(thumbnail);
        dest.writeString(measure20);
        dest.writeString(youtubeURL);
        dest.writeString(title);
        dest.writeString(measure12);
        dest.writeString(measure13);
        dest.writeString(measure10);
        dest.writeString(measure11);
        dest.writeString(measure9);
        dest.writeString(measure7);
        dest.writeString(measure8);
        dest.writeString(measure5);
        dest.writeString(measure6);
        dest.writeString(measure3);
        dest.writeString(measure4);
        dest.writeString(measure1);
        dest.writeString(measure18);
        dest.writeString(measure2);
        dest.writeString(measure19);
        dest.writeString(measure16);
        dest.writeString(measure17);
        dest.writeString(measure14);
        dest.writeString(measure15);
    }
}