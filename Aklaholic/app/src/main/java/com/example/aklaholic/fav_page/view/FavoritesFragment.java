package com.example.aklaholic.fav_page.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aklaholic.R;
import com.example.aklaholic.fav_page.presenter.FavPresenter;
import com.example.aklaholic.main.view.MainComm;
import com.example.aklaholic.model.local.MealLocalDataSource;
import com.example.aklaholic.model.pojo.FavouriteMeal;
import com.example.aklaholic.model.pojo.Meal;
import com.example.aklaholic.model.remote.MealRemoteDataSource;
import com.example.aklaholic.model.remote.MealRemoteDataSourceImpl;
import com.example.aklaholic.model.repo.MealRepository;

import java.io.File;
import java.util.List;

public class FavoritesFragment extends Fragment implements FavView{

    MainComm mainComm;
    RecyclerView recyclerView;
    FavPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_favorites);
        MealLocalDataSource mealLocalDataSource = MealLocalDataSource.getInstance(getContext());
        MealRemoteDataSource mealRemoteDataSource = MealRemoteDataSourceImpl.getInstance();
        MealRepository mealRepository = MealRepository.getInstance(mealLocalDataSource,mealRemoteDataSource);
        presenter = new FavPresenter(this, mealRepository);
        mainComm = (MainComm) getActivity();
        presenter.init();
    }

    @Override
    public File getFilesDir() {
        return getActivity().getFilesDir();
    }

    @Override
    public void setAdapter(List<FavouriteMeal> meals, List<Bitmap> bitmaps) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("ChangingRecycler", "setAdapter: ");
                Log.i("ChangingRecycler", "setAdapter: "+meals.size());
                Log.i("ChangingRecycler", "setAdapter: "+bitmaps.size());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                FavAdapter favAdapter = new FavAdapter(meals, bitmaps,FavoritesFragment.this);
                recyclerView.setAdapter(favAdapter);
            }
        });
    }

    @Override
    public void onClickMeal(Meal meal) {
        mainComm.showMealFromFav(meal.getIdMeal());
    }

    @Override
    public void onClickDelete(FavouriteMeal meal) {
        presenter.deleteMeal(meal);
    }
}