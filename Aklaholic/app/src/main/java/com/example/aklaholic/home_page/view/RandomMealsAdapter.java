package com.example.aklaholic.home_page.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aklaholic.R;
import com.example.aklaholic.model.pojo.Meal;

import java.util.ArrayList;
import java.util.List;


public class RandomMealsAdapter extends RecyclerView.Adapter<RandomMealsAdapter.ViewHolder>{

    private List<Meal> meals;
    private OnMealClickHome onMealClickHome;

    public RandomMealsAdapter(List<Meal> meals,OnMealClickHome onMealClickHome) {
        this.onMealClickHome = onMealClickHome;
        this.meals = meals;
    }

    @NonNull
    @Override
    public RandomMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(com.example.aklaholic.R.layout.item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomMealsAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        Glide.with(holder.itemView).load(meal.getStrMealThumb()).into(holder.imageView);
        holder.title.setText(meal.getStrMeal());
        holder.category.setText(meal.getStrCategory());
        holder.itemView.setOnClickListener(v -> {
            onMealClickHome.onMealClick(meal.getIdMeal());
        });
    }

    @Override
    public int getItemCount() {
        if(meals == null){
            return 0;
        }
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_meal);
            title = itemView.findViewById(R.id.text_meal_name);
            category = itemView.findViewById(R.id.text_meal_category);
        }
    }
}
