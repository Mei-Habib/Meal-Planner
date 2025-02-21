package com.example.mealplanner.model.categories;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("strCategory")
    private String title;

    @SerializedName("idCategory")
    private String id;

    @SerializedName("strCategoryThumb")
    private String thumbnail;

    public Category(String id, String thumbnail, String title) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return
                "Category{" +
                        ",id = '" + id + '\'' +
                        ",category = '" + title + '\'' +
                        ",thumbnail = '" + thumbnail + '\'' +
                        "}";
    }
}
