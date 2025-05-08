package com.example.aklaholic.main.view;

import com.example.aklaholic.model.pojo.FavouriteMeal;

public interface MainComm {
    public void showMealFromHome(String mealId);
    public void showMealFromFav(String mealId);
    public void showMealFromSearch(String mealId);
}
