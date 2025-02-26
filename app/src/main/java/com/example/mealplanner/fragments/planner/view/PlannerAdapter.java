package com.example.mealplanner.fragments.planner.view;


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
import com.example.mealplanner.model.Plan;
import com.example.mealplanner.model.recipes.Recipe;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.ViewHolder> {

    private Context context;
    private List<Plan> plans;
    private OnPlanClickListener listener;

    public PlannerAdapter(Context context, OnPlanClickListener listener, List<Plan> plans) {
        this.context = context;
        this.plans = plans;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(plans.get(position).getRecipeTitle());
        Glide.with(context).load(plans.get(position).getRecipeThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.cardView.setOnClickListener(view -> listener.onPlanClickListener(plans.get(position)));

        holder.delete.setOnClickListener(view -> listener.onDeleteClickListener(plans.get(position)));
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void updateList(List<Plan> plans) {
        this.plans = plans;
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

    public interface OnPlanClickListener {
        void onPlanClickListener(Plan plan);

        void onDeleteClickListener(Plan plan);
    }
}
