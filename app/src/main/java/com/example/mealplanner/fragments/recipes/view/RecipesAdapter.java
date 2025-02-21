package com.example.mealplanner.fragments.recipes.view;

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

import java.util.List;

/*
public class RecipesAdapter extends RecyclerView.Adapter<com.example.mealplanner.fragments.recipes.view.RecipesAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> categories;

    public RecipesAdapter(Context context, List<Recipe> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public com.example.mealplanner.fragments.recipes.view.RecipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        com.example.mealplanner.fragments.categories.view.CategoriesAdapter.ViewHolder viewHolder = new com.example.mealplanner.fragments.categories.view.CategoriesAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.mealplanner.fragments.recipes.view.RecipesAdapter.ViewHolder holder, int position) {
        holder.title.setText(categories.get(position).getTitle());
        Glide.with(context).load(categories.get(position).getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imv_thumbnail);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}

}
*/