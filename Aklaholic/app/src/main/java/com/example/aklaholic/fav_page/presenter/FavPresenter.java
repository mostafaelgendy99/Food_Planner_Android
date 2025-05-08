package com.example.aklaholic.fav_page.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.aklaholic.fav_page.view.FavView;
import com.example.aklaholic.model.local.MealLocalDataSource;
import com.example.aklaholic.model.pojo.FavouriteMeal;
import com.example.aklaholic.model.pojo.Meal;
import com.example.aklaholic.model.repo.MealRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FavPresenter {

    private FavView view;
    private MealRepository mealRepository;

    public FavPresenter(FavView view, MealRepository mealRepository) {
        this.view = view;
        this.mealRepository = mealRepository;
    }

    public void init(){
        class myThread extends Thread{
            private List<FavouriteMeal> meals;
            private List<Bitmap> bitmaps;
            public myThread(List<FavouriteMeal> meals) {
                this.meals = meals;
                this.bitmaps = new ArrayList<>();
            }

            @Override
            public void run() {
                File images = new File(view.getFilesDir(), "images");
                for(int i=0;i<meals.size();i++){
                    try{
                        File image = new File(images, meals.get(i).getStrMeal().replace(' ','_') + ".png");
                        if(image.exists()){
                            bitmaps.add(BitmapFactory.decodeFile(image.getAbsolutePath()));
                        }
                    }
                    catch (Exception e){

                    }
                }
                view.setAdapter(meals, bitmaps);
            }
        }

        mealRepository.getAllFavouriteMeals(new MealLocalDataSource.MealLocalOpInterface() {
            @Override
            public void onSuccess(List<FavouriteMeal> meals) {
                new myThread(meals).start();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void deleteMeal(FavouriteMeal meal){
        mealRepository.deleteFavouriteMeal(meal);
    }
}
