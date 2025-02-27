package com.example.mealplanner.fragments.recipes.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    public RecipesAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
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
        Glide.with(context).load(recipes.get(position).getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

//        holder.cardView.setOnClickListener(view -> listener.onPlanClickListener(plans.get(position)));

//        holder.delete.setOnClickListener(view -> listener.onDeleteClickListener(plans.get(position)));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void updateList(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardView;
        ImageView thumbnail;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewRecipe);
            thumbnail = itemView.findViewById(R.id.recipeThumbnail);
            title = itemView.findViewById(R.id.recipeTitle);
        }
    }

//    public interface OnPlanClickListener {
//        void onPlanClickListener(Plan plan);
//
//        void onDeleteClickListener(Plan plan);
//    }
}
