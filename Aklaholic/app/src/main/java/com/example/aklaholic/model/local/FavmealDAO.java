package com.example.aklaholic.model.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.aklaholic.model.pojo.FavouriteMeal;

import java.util.List;

@Dao
public interface FavmealDAO {
    @Insert
    void insertFavouriteMeal(FavouriteMeal favouriteMeal);
    @Delete
    void deleteFavouriteMeal(FavouriteMeal favouriteMeal);
    @Query("SELECT * FROM favourite_meal where idMeal = :id LIMIT 1")
    FavouriteMeal getFavouriteMealByMealID(String id);
    @Query("DELETE FROM favourite_meal where idMeal = :id")
    void deleteFavouriteMealByMealID(String id);
    @Query("SELECT * FROM favourite_meal where strMeal = :name LIMIT 1")
    FavouriteMeal getFavouriteMealByName(String name);

    @Query("SELECT * FROM favourite_meal")
    List<FavouriteMeal> getAllFavouriteMeals();

    @Query("DELETE FROM favourite_meal where strMeal = :name")
    void deleteFavouriteMealByName(String name);

    @Query("DELETE FROM favourite_meal")
    void deleteAllFavouriteMeals();
}
