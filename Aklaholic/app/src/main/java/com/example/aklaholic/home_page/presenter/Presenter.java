package com.example.aklaholic.home_page.presenter;



import com.example.aklaholic.home_page.view.IView;
import com.example.aklaholic.model.pojo.Meal;
import com.example.aklaholic.model.remote.MealListNetworkCB;
import com.example.aklaholic.model.remote.MealRemoteDataSource;
import com.example.aklaholic.model.remote.MealRemoteDataSourceImpl;
import com.example.aklaholic.model.remote.OneMealNetworkCB;

import java.util.List;

public class Presenter implements IPresenter {

    private IView view;
    private MealRemoteDataSource remoteDataSource;

    public Presenter(IView view) {
        this.view = view;
        this.remoteDataSource = MealRemoteDataSourceImpl.getInstance();
    }


    @Override
    public void getRandomMeal() {
        remoteDataSource.getRandomMeal(new OneMealNetworkCB() {
            @Override
            public void onSingleMealSuccess(Meal meal) {
                if (view != null) {
                    view.showRandomMeal(meal);
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (view != null) {
                    view.showError(errorMsg);
                }
            }
        });
    }

    @Override
    public void getRandomMeals(){
        remoteDataSource.getMealsByCountry("Egyptian", new MealListNetworkCB() {
            @Override
            public void onSuccess(List<Meal> meals) {
                view.showRandomMeals(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });

    }


    @Override
    public void onDestroy() {
        view = null;
    }
}