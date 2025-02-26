package com.example.mealplanner.fragments.home.view;

import android.content.Context;
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
import com.example.mealplanner.model.recipes.Recipe;
import com.google.android.material.card.MaterialCardView;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipes;
    private OnRecipeClickListener onRecipeClickListener;

    public HomeAdapter(Context context, OnRecipeClickListener onRecipeClickListener, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
        this.onRecipeClickListener = onRecipeClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(recipes.get(position).getTitle());
        holder.preparationTime.setText("---");
        holder.category.setText(recipes.get(position).getCategory());
        Glide.with(context).load(recipes.get(position).getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.cardView.setOnClickListener(view -> {
            onRecipeClickListener.onRecipeClickListener(recipes.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardView;
        ImageView thumbnail;
        TextView title;
        TextView preparationTime;
        TextView category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView_recipe);
            thumbnail = itemView.findViewById(R.id.imv_recipe_thumbnail);
            title = itemView.findViewById(R.id.tv_recipe_title);
            preparationTime = itemView.findViewById(R.id.tv_prepTime);
            category = itemView.findViewById(R.id.tv_recipe_category);
        }
    }

    public interface OnRecipeClickListener {
        void onRecipeClickListener(Recipe recipe);
    }
}
