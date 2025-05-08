package com.example.aklaholic.home_page.view;

import com.example.aklaholic.model.pojo.Meal;

import java.util.List;


public interface IView {
        void showRandomMeal(Meal meal);
        void showRandomMeals(List<Meal> meals);
        void showError(String errorMsg);
    }

