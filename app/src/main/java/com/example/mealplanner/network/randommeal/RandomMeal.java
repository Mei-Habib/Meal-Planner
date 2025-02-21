package com.example.mealplanner.network.randommeal;

import com.google.gson.annotations.SerializedName;

public class RandomMeal {

    @SerializedName("idMeal")
    private String id;

    @SerializedName("strMeal")
    private String title;

    @SerializedName("strMealThumb")
    private String thumbnail;

    @SerializedName("strCategory")
    private String category;


    @SerializedName("strTags")
    private String tags;

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public String getId() {
        return id;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return
                "RandomMeal{" +
                        ",category = '" + category + '\'' +
                        ",tags = '" + tags + '\'' +
                        ",id = '" + id + '\'' +
                        ",thumbnail = '" + thumbnail + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }
}