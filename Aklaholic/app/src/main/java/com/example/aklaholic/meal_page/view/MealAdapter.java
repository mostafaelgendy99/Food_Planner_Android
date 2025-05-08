package com.example.aklaholic.meal_page.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aklaholic.R;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private List<String> ingredientNames;
    private List<String> ingredientMeasurements;

    public MealAdapter(List<String> ingredientNames, List<String> ingredientMeasurements) {
        this.ingredientNames = ingredientNames;
        this.ingredientMeasurements = ingredientMeasurements;
    }


    @NonNull
    @Override
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {
        holder.ingredientName.setText(ingredientNames.get(position));
        holder.ingredientMeasure.setText(ingredientMeasurements.get(position));
    }

    @Override
    public int getItemCount() {
        if(ingredientNames != null)return ingredientNames.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView ingredientName;
        public TextView ingredientMeasure;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientMeasure = itemView.findViewById(R.id.ingredient_measurement);
        }
    }
}
