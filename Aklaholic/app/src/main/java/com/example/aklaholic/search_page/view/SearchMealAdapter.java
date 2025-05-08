package com.example.aklaholic.search_page.view;

import android.util.Log;
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

import java.util.List;

public class SearchMealAdapter extends RecyclerView.Adapter<SearchMealAdapter.ViewHolder> {

    private List<Meal> meals;
    private OnMealClick mealClickListener;

    public SearchMealAdapter(List<Meal> meals, OnMealClick mealClickListener) {
        this.meals = meals;
        this.mealClickListener = mealClickListener;
    }

    @NonNull
    @Override
    public SearchMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMealAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());
        holder.mealCategory.setText(meal.getStrCategory());
        String imageUrl = meal.getStrMealThumb();
        Log.i("ImageURL", imageUrl);
        Glide.with(holder.itemView).load(imageUrl).into(holder.mealImage);

        holder.itemView.setOnClickListener(v -> {
            mealClickListener.onMealClick(meal.getIdMeal());
        });
    }

    @Override
    public int getItemCount() {
        if(meals == null){
            return 0;
        }
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mealImage;
        public TextView mealName;
        public TextView mealCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.image_meal);
            mealName = itemView.findViewById(R.id.text_meal_name);
            mealCategory = itemView.findViewById(R.id.text_meal_category);

        }

    }
}
