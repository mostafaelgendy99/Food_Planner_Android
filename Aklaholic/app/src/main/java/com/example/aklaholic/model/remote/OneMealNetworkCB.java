package com.example.aklaholic.model.remote;

import com.example.aklaholic.model.pojo.Meal;


public interface OneMealNetworkCB {
    public void onSingleMealSuccess(Meal meal);
    public void onFailure(String errorMessage);
}
