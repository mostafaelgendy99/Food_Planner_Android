package com.example.aklaholic.search_page.presenter;

import com.example.aklaholic.model.pojo.Category;
import com.example.aklaholic.model.pojo.Ingredient;
import com.example.aklaholic.model.pojo.Meal;
import com.example.aklaholic.model.remote.CategoryNetworkCB;
import com.example.aklaholic.model.remote.IngredientsNetworkCB;
import com.example.aklaholic.model.remote.MealListNetworkCB;
import com.example.aklaholic.model.repo.MealRepository;
import com.example.aklaholic.search_page.view.ISearchView;

import java.util.List;

public class SearchPresenter implements CategoryNetworkCB, IngredientsNetworkCB {
    private MealRepository mealRepository;
    private ISearchView view;

    public SearchPresenter(ISearchView view, MealRepository mealRepository) {
        this.view = view;
        this.mealRepository = mealRepository;
    }
    public void getAllCategories() {
        mealRepository.getAllCategories(this);
    }

    public void getAllIngredients() {
        mealRepository.getAllIngredients(this);
    }

    @Override
    public void onCategorySuccess(List<Category> categories) {
        view.showCategories(categories);
    }

    @Override
    public void onIngredientSuccess(List<Ingredient> ingredients) {
        view.showIngredients(ingredients);
    }

    @Override
    public void onFailure(String errorMessage) {
        view.showError(errorMessage);
    }

    public void getMealsByCountry(String country) {
        mealRepository.getMealsByCountry(country, new MealListNetworkCB() {
            @Override
            public void onSuccess(List<Meal> meals) {
                view.showMeals(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    public void getMealsByCategory(String category){
        mealRepository.getMealsByCategory(category, new MealListNetworkCB() {
            @Override
            public void onSuccess(List<Meal> meals) {
                view.showMeals(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    public void getMealsByIngredient(String ingredient){
        mealRepository.getMealsByIngredient(ingredient, new MealListNetworkCB() {
            @Override
            public void onSuccess(List<Meal> meals) {
                view.showMeals(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}
