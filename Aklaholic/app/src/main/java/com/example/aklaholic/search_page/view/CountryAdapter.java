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
import com.example.aklaholic.model.pojo.Country;

import java.util.Arrays;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Country> countries;
    private OnCountryClickListener countryClickListener;

    public CountryAdapter(List<Country>countries,OnCountryClickListener listener) {
        this.countryClickListener = listener;
        this.countries = countries;
    }


    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.title.setText(country.getCountryName());

        Glide.with(holder.itemView.getContext())
                .load(country.getImageResourceId()) // Use the drawable resource ID
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> countryClickListener.onCountryClick(country));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_country_flag);
            title = itemView.findViewById(R.id.text_country_name);
        }
    }


}