package com.example.mealplanner.fragments.explore.view;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.model.categories.Category;
import com.example.mealplanner.model.countries.Country;
import com.example.mealplanner.model.ingredients.Ingredient;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private List list;
    public static final int CATEGORY_LAYOUT = 1;
    public static final int COUNTRY_LAYOUT = 2;
    public static final int INGREDIENT_LAYOUT = 3;
    private int layout;
    private OnItemClickListener listener;

    public ExploreAdapter(Context context, OnItemClickListener listener, int layout, List<?> list) {
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        ViewHolder viewHolder = null;

        if (layout == CATEGORY_LAYOUT) {
            itemView = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
            viewHolder = new ViewHolder(itemView);
        } else if (layout == COUNTRY_LAYOUT) {
            itemView = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);
            viewHolder = new ViewHolder(itemView);
        } else if (layout == INGREDIENT_LAYOUT) {
            itemView = LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false);
            viewHolder = new ViewHolder(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (layout == CATEGORY_LAYOUT) {
            Category category = (Category) list.get(position);
            holder.title.setText(category.getTitle());
            Glide.with(context).load(category.getThumbnail())
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail);
            holder.thumbnail.setOnClickListener(v -> listener.onItemClick(category.getTitle(), CATEGORY_LAYOUT));

        } else if (layout == COUNTRY_LAYOUT) {
            Country country = (Country) list.get(position);
            holder.title.setText(country.getCountry());
            holder.thumbnail.setImageResource(country.getThumbnail().get(position));
            holder.thumbnail.setOnClickListener(v -> listener.onItemClick(country.getCountry(), COUNTRY_LAYOUT));

        } else if (layout == INGREDIENT_LAYOUT) {
            String thumbnailUrl;
            Ingredient ingredient = (Ingredient) list.get(position);
            thumbnailUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getIngredient() + ".png";
            holder.title.setText(ingredient.getIngredient());
            Glide.with(context).load(thumbnailUrl)
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail);
            holder.thumbnail.setOnClickListener(v -> listener.onItemClick(ingredient.getIngredient(), INGREDIENT_LAYOUT));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(int layout, List<?> list) {
        Log.i("TAG", "updateList: " + list.get(0));
        this.list = list;
        this.layout = layout;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (layout == CATEGORY_LAYOUT) {
                thumbnail = itemView.findViewById(R.id.imv_category);
                title = itemView.findViewById(R.id.tv_category);
                cardView = itemView.findViewById(R.id.cardViewCategory);
            } else if (layout == COUNTRY_LAYOUT) {
                thumbnail = itemView.findViewById(R.id.imv_country);
                title = itemView.findViewById(R.id.tv_country);
            } else if (layout == INGREDIENT_LAYOUT) {
                title = itemView.findViewById(R.id.tv_ingredient_title);
                thumbnail = itemView.findViewById(R.id.imv_ingredient_thumbnail);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String title, int searchBy);
    }
}

