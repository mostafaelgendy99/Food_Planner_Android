package com.example.aklaholic.model.remote;

import com.example.aklaholic.model.pojo.Meal;

import java.util.List;

public interface MealListNetworkCB {
    public void onSuccess(List<Meal> meals);
    public void onFailure(String errorMessage);
}
