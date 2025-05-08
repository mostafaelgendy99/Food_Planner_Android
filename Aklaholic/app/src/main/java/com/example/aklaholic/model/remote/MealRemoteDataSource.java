package com.example.aklaholic.model.remote;


public interface MealRemoteDataSource {
    void getRandomMeal(OneMealNetworkCB callback);
    void getMealsByCategory(String category, MealListNetworkCB callback);
    void getMealsByIngredient(String ingredient, MealListNetworkCB callback);
    void getMealsByCountry(String country, MealListNetworkCB callback);
    void getMealById(String id, OneMealNetworkCB callback);
    void getAllCategories(CategoryNetworkCB callback);
    void getAllIngredients(IngredientsNetworkCB callback);

}