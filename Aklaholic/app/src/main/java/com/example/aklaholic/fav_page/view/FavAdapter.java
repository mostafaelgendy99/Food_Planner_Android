package com.example.aklaholic.fav_page.view;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aklaholic.R;
import com.example.aklaholic.model.pojo.FavouriteMeal;
import com.example.aklaholic.model.pojo.Meal;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private List<FavouriteMeal> meals;
    private List<Bitmap> bitmaps;
    private FavView favview;

    public FavAdapter(List<FavouriteMeal> meals, List<Bitmap> bitmaps,FavView view) {
        this.meals = meals;
        this.bitmaps = bitmaps;
        this.favview = view;
    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_favourite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.textView.setText(meal.getStrMeal());

        final int pos = position;

        if(position >= bitmaps.size())
            return;
        if(bitmaps.get(position)!=null){
            Glide.with(holder.itemView.getContext())
                    .asBitmap()
                    .load(bitmaps.get(position))
                    .into(holder.imageView);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favview.onClickMeal(meal);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favview.onClickDelete(new FavouriteMeal(meal));
                meals.remove(pos);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(meals != null)
            return meals.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_favorite_meal);
            textView = itemView.findViewById(R.id.text_favorite_meal_title);
            button  = itemView.findViewById(R.id.button_delete_favorite);
        }
    }
}
