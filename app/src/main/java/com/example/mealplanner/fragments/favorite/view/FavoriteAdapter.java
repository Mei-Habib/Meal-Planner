package com.example.mealplanner.fragments.favorite.view;

import android.content.Context;
import android.util.Log;
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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> favoriteRecipes;
    private OnRecipeClickListener onRecipeClickListener;

    public FavoriteAdapter(Context context, OnRecipeClickListener onRecipeClickListener, List<Recipe> favoriteRecipes) {
        this.context = context;
        this.favoriteRecipes = favoriteRecipes;
        this.onRecipeClickListener = onRecipeClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        FavoriteAdapter.ViewHolder viewHolder = new FavoriteAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(favoriteRecipes.get(position).getTitle());
        Glide.with(context).load(favoriteRecipes.get(position).getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.cardView.setOnClickListener(view -> {
//            onRecipeClickListener.onRecipeClickListener(recipes.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return favoriteRecipes.size();
    }

    public void updateList(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardView;
        ImageView thumbnail;
        TextView title;
        ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView_favorite);
            thumbnail = itemView.findViewById(R.id.imv_favorite);
            title = itemView.findViewById(R.id.tv_favorite_title);
            delete = itemView.findViewById(R.id.btn_favorite_delete);
        }
    }

    public interface OnRecipeClickListener {
        void onRecipeClickListener(Recipe recipe);
    }
}
