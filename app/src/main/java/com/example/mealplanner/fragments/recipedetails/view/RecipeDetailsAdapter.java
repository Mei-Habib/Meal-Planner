package com.example.mealplanner.fragments.recipedetails.view;

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
import com.example.mealplanner.fragments.recipedetails.presenter.RecipeDetailsPresenter;
import com.example.mealplanner.model.RecipesRepository;
import com.example.mealplanner.data.local.room.database.RecipesLocalDataSource;
import com.example.mealplanner.model.recipes.Recipe;
import com.example.mealplanner.network.RecipeRemoteDataSource;


public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.ViewHolder> {

    private Context context;
    private Recipe recipe;
    private int count;
    private String numberOnly;
    private final RecipeDetailsPresenter presenter;
    private RecipeDetailsView view;

    public RecipeDetailsAdapter(Context context, RecipeDetailsView view, int count, Recipe recipe) {
        this.context = context;
        this.view = view;
        this.recipe = recipe;
        this.count = count;
        presenter = new RecipeDetailsPresenter(RecipesRepository.getInstance(new RecipeRemoteDataSource(), new RecipesLocalDataSource(context)), view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recipe_ingredient_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < recipe.getIngredients().size() && position < recipe.getMeasures().size()) {
            String thumbnailUrl = "https://www.themealdb.com/images/ingredients/" + recipe.getIngredients().get(position) + ".png";
            holder.title.setText(recipe.getIngredients().get(position));
            numberOnly = recipe.getMeasures().get(position).replaceAll("[^0-9]", "");
            if (!numberOnly.isEmpty()) {
//                Log.i("TAG", "onBindViewHolder: " + Integer.parseInt(numberOnly)*2);
                holder.measure.setText((Integer.parseInt(numberOnly) * count) + "");
            }
            Glide.with(context).load(thumbnailUrl)
                    .apply(new RequestOptions().override(200, 200))
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail);

        }
    }

    @Override
    public int getItemCount() {
        return recipe.getIngredients().size();
    }

    public void updateList(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView measure;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imv_recipeIngredient);
            title = itemView.findViewById(R.id.tv_recipeIngredientTitle);
            measure = itemView.findViewById(R.id.tv_recipeIngredientMeasure);
        }
    }

}
