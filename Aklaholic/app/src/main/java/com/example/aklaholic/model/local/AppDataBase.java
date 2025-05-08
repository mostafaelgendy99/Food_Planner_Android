package com.example.aklaholic.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aklaholic.model.pojo.FavouriteMeal;

@Database(entities = {FavouriteMeal.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;
    public abstract FavmealDAO getFavDao();
    public static synchronized AppDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                            context,
                            AppDataBase.class,"myDB.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        instance.getOpenHelper().getWritableDatabase();
        return instance;
    }
}
