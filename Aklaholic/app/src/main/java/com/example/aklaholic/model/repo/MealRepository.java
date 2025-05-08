package com.example.aklaholic.model.repo;

import com.example.aklaholic.model.local.MealLocalDataSource;
import com.example.aklaholic.model.pojo.FavouriteMeal;
import com.example.aklaholic.model.remote.CategoryNetworkCB;
import com.example.aklaholic.model.remote.IngredientsNetworkCB;
import com.example.aklaholic.model.remote.MealListNetworkCB;
import com.example.aklaholic.model.remote.MealRemoteDataSource;
import com.example.aklaholic.model.remote.OneMealNetworkCB;

public class MealRepository {

    private MealLocalDataSource mealLocalDataSource;
    private MealRemoteDataSource mealRemoteDataSource;

    private MealRepository(MealLocalDataSource mealLocalDataSource,MealRemoteDataSource mealRemoteDataSource){
        this.mealLocalDataSource = mealLocalDataSource;
        this.mealRemoteDataSource = mealRemoteDataSource;
    }

    public static MealRepository getInstance(MealLocalDataSource mealLocalDataSource,MealRemoteDataSource mealRemoteDataSource){
        return new MealRepository(mealLocalDataSource,mealRemoteDataSource);
    }

    public void insertFavouriteMeal(FavouriteMeal favouriteMeal){
        mealLocalDataSource.insertFavouriteMeal(favouriteMeal);
    }

    public void deleteFavouriteMeal(FavouriteMeal favouriteMeal){
        mealLocalDataSource.deleteFavouriteMeal(favouriteMeal);
    }


    public void getAllFavouriteMeals(MealLocalDataSource.MealLocalOpInterface cb){
        mealLocalDataSource.getAllFavouriteMeals(cb);
    }

    public void getRandomMeal(OneMealNetworkCB cb){
        mealRemoteDataSource.getRandomMeal(cb);
    }

    public void getMealById(String mealId, OneMealNetworkCB cb){
        mealRemoteDataSource.getMealById(mealId,cb);
    }

    public void getMealsByCategory(String category, MealListNetworkCB callback){
        mealRemoteDataSource.getMealsByCategory(category,callback);
    }

    public void getFavMealById(String mealId, MealLocalDataSource.MealLocalOpInterface callback){
        mealLocalDataSource.getFavMealById(mealId,callback);
    }
    public void getAllCategories(CategoryNetworkCB callback){
        mealRemoteDataSource.getAllCategories(callback);
    }
    public void getAllIngredients(IngredientsNetworkCB callback){
        mealRemoteDataSource.getAllIngredients(callback);
    }

    public void getMealsByIngredient(String ingredient, MealListNetworkCB callback){
        mealRemoteDataSource.getMealsByIngredient(ingredient,callback);
    }
    public void getMealsByCountry(String country, MealListNetworkCB callback){
        mealRemoteDataSource.getMealsByCountry(country,callback);
    }

}
