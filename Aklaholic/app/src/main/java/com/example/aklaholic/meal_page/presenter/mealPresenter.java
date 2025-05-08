package com.example.aklaholic.meal_page.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.aklaholic.meal_page.view.Iview;
import com.example.aklaholic.model.local.MealLocalDataSource;
import com.example.aklaholic.model.pojo.FavouriteMeal;
import com.example.aklaholic.model.pojo.Meal;
import com.example.aklaholic.model.remote.OneMealNetworkCB;
import com.example.aklaholic.model.repo.MealRepository;

import java.io.File;
import java.util.List;

public class mealPresenter {

    private MealRepository mealRepository;
    private Iview iview;

    public mealPresenter(MealRepository mealRepository,Iview iview){
        this.iview = iview;
        this.mealRepository = mealRepository;
    }


    public void getMealDetails(String mealId) {
        mealRepository.getMealById(mealId, new OneMealNetworkCB() {
            @Override
            public void onSingleMealSuccess(Meal meal) {
                iview.showMeal(meal);
            }

            @Override
            public void onFailure(String errorMessage) {
                iview.showError("can't show meal");
            }
        });
    }

    public void getFavMealDetails(String mealId) {
        mealRepository.getFavMealById(mealId, new MealLocalDataSource.MealLocalOpInterface() {
            @Override
            public void onSuccess(List<FavouriteMeal> meals) {
                if(meals.size() > 0) {
                    Bitmap bitmap;
                    File images = new File(iview.getFilesDir(), "images");
                    try{
                        File image = new File(images, meals.get(0).getStrMeal().replace(' ','_') + ".png");
                        bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
                        Meal meal = (Meal) meals.get(0);
                        iview.showFavMeal(meal,bitmap);
                    }
                    catch (Exception e){

                    }
                } else {
                    iview.showError("can't show meal");
                }
            }

            @Override
            public void onFailure() {
                iview.showError("can't show meal");
            }
        });

    }

    public void saveMealToFav(Meal meal) {
        FavouriteMeal favouriteMeal = new FavouriteMeal(meal);
        mealRepository.insertFavouriteMeal(favouriteMeal);
    }
}
