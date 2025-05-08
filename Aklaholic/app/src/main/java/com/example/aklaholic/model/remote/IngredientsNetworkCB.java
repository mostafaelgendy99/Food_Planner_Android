package com.example.aklaholic.model.remote;

import com.example.aklaholic.model.pojo.Ingredient;

import java.util.List;

public interface IngredientsNetworkCB {
    public void onIngredientSuccess(List<Ingredient> ingredients);
    public void onFailure(String errorMessage);
}