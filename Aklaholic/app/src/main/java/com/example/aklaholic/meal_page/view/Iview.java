package com.example.aklaholic.meal_page.view;

import android.graphics.Bitmap;

import com.example.aklaholic.model.pojo.Meal;

import java.io.File;

public interface Iview {
    void showMeal(Meal meal);
    void showError(String errorMessage);
    void showFavMeal(Meal meal, Bitmap bitmap);
    File getFilesDir();
}
