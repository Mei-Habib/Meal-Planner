package com.example.mealplanner.fragments.explore.view;


import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealplanner.R;
import com.example.mealplanner.network.categories.Category;
import com.example.mealplanner.network.country.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private Context context;
    private List list;
    private static final int CATEGORY_LAYOUT = 1;
    private static final int COUNTRY_LAYOUT = 2;
    private static final int INGREDIENT_LAYOUT = 3;
    private int layout;
//    private List<Category> categories = new ArrayList<>();
//    private List<Country> countries = new ArrayList<>();
//    private List<Ingredient> ingredients;

    public ExploreAdapter(Context context, int layout, List<?> list) {
        this.context = context;
        this.list = list;
        this.layout = layout;
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
            itemView = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
            viewHolder = new ViewHolder(itemView);
        } else if (layout == INGREDIENT_LAYOUT) {
            itemView = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
            viewHolder = new ViewHolder(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (layout == CATEGORY_LAYOUT) {
//            categories = (List<Category>) list.stream().map(e -> (Category) e).collect(Collectors.toList());
            Category category = (Category) list.get(position);
            holder.title.setText(category.getTitle());
            Glide.with(context).load(category.getThumbnail())
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.thumbnail);
        } else if (layout == COUNTRY_LAYOUT) {
//            countries = (List<Country>) list.stream().map(e -> (Country) e).collect(Collectors.toList());
            Country country = (Country) list.get(position);
            holder.title.setText(country.getCountry());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<?> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imv_category);
            title = itemView.findViewById(R.id.tv_category_title);
        }
    }
}

