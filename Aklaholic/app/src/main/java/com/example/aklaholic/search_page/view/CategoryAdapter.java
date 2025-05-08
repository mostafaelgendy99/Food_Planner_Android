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
import com.example.aklaholic.main.view.MainComm;
import com.example.aklaholic.model.pojo.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categories;
    private OnCategoryClickListener categoryClickListener;

    public CategoryAdapter(List<Category> categories, OnCategoryClickListener categoryClickListener) {
        this.categories = categories;
        this.categoryClickListener = categoryClickListener;
    }

    public void setFilteredList(List<Category> filteredList) {
        this.categories = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.title.setText(category.getStrCategory());

        Glide.with(holder.itemView.getContext()).load(category.getStrCategoryThumb()).into(holder.image);

        holder.itemView.setOnClickListener(v -> categoryClickListener.onCategoryClick(category));
    }

    @Override
    public int getItemCount() {
        if(categories == null)return 0;
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_country_flag);
            title = itemView.findViewById(R.id.text_country_name);
        }
    }

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }
}