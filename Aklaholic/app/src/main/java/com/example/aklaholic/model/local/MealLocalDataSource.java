package com.example.aklaholic.model.local;
import android.content.Context;
import com.example.aklaholic.model.pojo.FavouriteMeal;

import java.util.List;




public class MealLocalDataSource{
    private static MealLocalDataSource instance = null;
    private AppDataBase appDataBase;
    private FavmealDAO favmealDAO;

    public interface MealLocalOpInterface{
        public void onSuccess(List<FavouriteMeal> meals);
        public void onFailure();
    }

    public static MealLocalDataSource getInstance(Context context){
        if(instance == null){
            instance = new MealLocalDataSource(context);
        }
        return instance;
    }

    private MealLocalDataSource(Context context) {
        appDataBase = AppDataBase.getInstance(context);
        favmealDAO = appDataBase.getFavDao();
    }

    public FavmealDAO getFavouriteMealDao() {
        return appDataBase.getFavDao();
    }

    public void insertFavouriteMeal(FavouriteMeal favouriteMeal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (favmealDAO.getFavouriteMealByMealID(favouriteMeal.getIdMeal()) == null) {
                    favmealDAO.insertFavouriteMeal(favouriteMeal);
                }
            }
        }).start();
    }

    public void deleteFavouriteMeal(FavouriteMeal favouriteMeal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (favmealDAO.getFavouriteMealByMealID(favouriteMeal.getIdMeal()) != null) {
                    favmealDAO.deleteFavouriteMealByMealID(favouriteMeal.getIdMeal());
                }
            }
        }).start();
    }

    public void getAllFavouriteMeals(MealLocalOpInterface mealLocalOpInterface){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<FavouriteMeal> meals = favmealDAO.getAllFavouriteMeals();
                if(meals != null && meals.size() > 0){
                    mealLocalOpInterface.onSuccess(meals);
                }else{
                    mealLocalOpInterface.onFailure();
                }
            }
        }).start();
    }

    public void getFavMealById(String mealId, MealLocalOpInterface mealLocalOpInterface){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FavouriteMeal meal = favmealDAO.getFavouriteMealByMealID(mealId);
                if(meal != null){
                    mealLocalOpInterface.onSuccess(List.of(meal));
                }else{
                    mealLocalOpInterface.onFailure();
                }
            }
        }).start();
    }

}