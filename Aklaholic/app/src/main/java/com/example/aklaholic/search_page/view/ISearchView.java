package com.example.aklaholic.search_page.view;

import com.example.aklaholic.model.pojo.Category;
import com.example.aklaholic.model.pojo.Country;
import com.example.aklaholic.model.pojo.Ingredient;
import com.example.aklaholic.model.pojo.Meal;

import java.util.List;

public interface ISearchView {
    void showCategories(List<Category> categories);
    void showError(String errorMessage);
    void showIngredients(List<Ingredient> ingredients);
    void showCountries(List<Country> countries);
    void showMeals(List<Meal> meals);
}
