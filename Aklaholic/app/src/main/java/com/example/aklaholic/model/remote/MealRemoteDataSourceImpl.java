package com.example.aklaholic.model.remote;

import com.example.aklaholic.model.pojo.CategoryResponse;

import com.example.aklaholic.model.pojo.IngredientResponse;
import com.example.aklaholic.model.pojo.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MealRemoteDataSourceImpl implements MealRemoteDataSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String FLAGS_URL = "https://flagsapi.com/";
    private static MealRemoteDataSource instance;
    private MealRetrofitService service;

    private MealRemoteDataSourceImpl() {
        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MealRetrofitService.class);
    }

    public static MealRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new MealRemoteDataSourceImpl();
        }
        return instance;
    }

    private void mealsCB(Call<MealResponse> call, MealListNetworkCB callback) {
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                } else {
                    callback.onFailure("Empty or failed response");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
    private void singlMealCB(Call<MealResponse> call, OneMealNetworkCB callback) {
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSingleMealSuccess(response.body().getMeals().get(0));
                } else {
                    callback.onFailure("Empty or failed response");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getRandomMeal(OneMealNetworkCB callback) {
        singlMealCB(service.getRandomMeal(), callback);
    }

    @Override
    public void getMealsByCategory(String category, MealListNetworkCB callback) {
        mealsCB(service.getMealsByCategory(category), callback);
    }

    @Override
    public void getMealsByIngredient(String ingredient, MealListNetworkCB callback) {
        mealsCB(service.getMealsByIngredient(ingredient), callback);
    }

    @Override
    public void getMealsByCountry(String country, MealListNetworkCB callback) {
        mealsCB(service.getMealsByCountry(country), callback);
    }

    @Override
    public void getMealById(String id, OneMealNetworkCB callback) {
        service.getMealById(id).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSingleMealSuccess(response.body().getMeals().get(0));
                } else {
                    callback.onFailure("Empty or failed response");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getAllCategories(CategoryNetworkCB callback) {
        service.getAllCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onCategorySuccess(response.body().getCategories());
                } else {
                    callback.onFailure("Empty or failed response");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getAllIngredients(IngredientsNetworkCB callback) {
        service.getAllIngredients().enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onIngredientSuccess(response.body().getIngredients());
                } else {
                    callback.onFailure("Empty or failed response");
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}