package com.example.aklaholic.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.aklaholic.fav_page.view.FavoritesFragment;
import com.example.aklaholic.R;
import com.example.aklaholic.home_page.view.HomeFragment;
import com.example.aklaholic.meal_page.view.MealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity  implements MainComm{
    private FragmentManager fragmentManager;
    private BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int HomeId = R.id.menu_home;

        navBar = findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, new HomeFragment())
                    .commit();
            navBar.setSelectedItemId(HomeId); // make Home item selected
        }

        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == HomeId) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container_view, new HomeFragment())
                            .commit();

                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container_view, new FavoritesFragment())
                            .commit();
                }
                return true;
            }
        });

    }

    @Override
    public void showMealFromHome(String mealId) {
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra("mealId", mealId);
        intent.putExtra("senderID", "home");
        startActivity(intent);
    }

    @Override
    public void showMealFromFav(String mealId) {
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra("mealId", mealId);
        intent.putExtra("senderID", "fav");
        startActivity(intent);
    }

    @Override
    public void showMealFromSearch(String mealId) {
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra("mealId", mealId);
        intent.putExtra("senderID", "search");
        startActivity(intent);
    }

}
