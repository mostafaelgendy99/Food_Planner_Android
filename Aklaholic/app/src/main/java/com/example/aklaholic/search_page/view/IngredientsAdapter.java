package com.example.aklaholic.search_page.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aklaholic.R;
import com.example.aklaholic.model.pojo.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> ingredients;
    private OnIngredientClickListener ingredientClickListener;

    public IngredientsAdapter(List<Ingredient> ingredients, OnIngredientClickListener ingredientClickListener) {
        this.ingredients = ingredients;
        this.ingredientClickListener = ingredientClickListener;
    }

    public void setFilteredList(List<Ingredient> filteredList) {
        this.ingredients = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.name.setText(ingredient.getStrIngredient());

        String ingredientName = ingredient.getStrIngredient().replace(" ", "_");
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientName + "-Small.png";


        holder.itemView.setOnClickListener(v -> ingredientClickListener.onIngredientClick(ingredient));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView measurement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredient_name);
            measurement = itemView.findViewById(R.id.ingredient_measurement);
        }
    }

    public interface OnIngredientClickListener {
        void onIngredientClick(Ingredient ingredient);
    }
}