package com.example.aklaholic.fav_page.view;

import android.graphics.Bitmap;

import com.example.aklaholic.model.pojo.FavouriteMeal;
import com.example.aklaholic.model.pojo.Meal;

import java.io.File;
import java.util.List;

public interface FavView {
    File getFilesDir();
    void setAdapter(List<FavouriteMeal> meals, List<Bitmap> bitmaps);
    void onClickMeal(Meal meal);

    void onClickDelete(FavouriteMeal meal);

}
