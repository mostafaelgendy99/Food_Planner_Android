package com.example.aklaholic.model.remote;

import com.example.aklaholic.model.pojo.CategoryResponse;
import com.example.aklaholic.model.pojo.IngredientResponse;
import com.example.aklaholic.model.pojo.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealRetrofitService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse> getAllCategories();

    @GET("list.php?i=list")
    Call<IngredientResponse> getAllIngredients();

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String country);

    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i") String id);

}